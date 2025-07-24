package mutsa.hackathon.match.service;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.match.entity.Matching;
import mutsa.hackathon.match.repository.MatchRepository;
import mutsa.hackathon.users.entity.Users;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PayService {
    private final MatchRepository matchRepository;

    public Map<Long, Integer> calculateEachPayment(Long matchId, double totalFare){
        List<Matching> matched = matchRepository.findAllByMatchId(matchId);

        Map<Long, Double> userDistances = new LinkedHashMap<>(); // 순서 유지
        double totalDistance = 0;

        for (Matching match : matched) {
            Users user = match.getUser();
            String departure = match.getDeparture();
            String destination = user.getDestination();

            double[] from = getLatLong(departure);
            double[] to = getLatLong(destination);

            double distance = haversine(from[0], from[1], to[0], to[1]);
            userDistances.put(user.getUserId(), distance);
            totalDistance += distance;
        }

        Map<Long, Integer> result = new LinkedHashMap<>();
        int remainingFare = (int) Math.round(totalFare); // 정수형 처리
        Long lastUserId = null;

        for (Map.Entry<Long, Double> entry : userDistances.entrySet()) {
            Long userId = entry.getKey();
            double userDistance = entry.getValue();

            double ratio = userDistance / totalDistance;
            int userPay = (int) Math.floor(ratio * totalFare);
            result.put(userId, userPay);
            remainingFare -= userPay;

            lastUserId = userId;
        }

        // 잔액 보정: 마지막 유저에게 차액 더해줌
        if (lastUserId != null && remainingFare > 0) {
            result.put(lastUserId, result.get(lastUserId) + remainingFare);
        }

        return result;
    }

    public double getKM(String from, String to) {
        double[] fromCoords = getLatLong(from);
        double[] toCoords = getLatLong(to);

        return haversine(fromCoords[0], fromCoords[1], toCoords[0], toCoords[1]);
    }
    // 위경도 가져오는 메서드
    public double[] getLatLong(String query) {
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String url = "https://nominatim.openstreetmap.org/search?q=" + encodedQuery + "&format=json";

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "mutsa-hackathon/1.0 (contact@minji@gmail.com)");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class
            );

            JSONArray results = new JSONArray(response.getBody());
            JSONObject location = results.getJSONObject(0);
            double lat = Double.parseDouble(location.getString("lat"));
            double lon = Double.parseDouble(location.getString("lon"));

            return new double[]{lat, lon};
        } catch (Exception e) {
            throw new RuntimeException("위경도 조회 실패: " + query, e);
        }
    }


    // Haversine 공식
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
