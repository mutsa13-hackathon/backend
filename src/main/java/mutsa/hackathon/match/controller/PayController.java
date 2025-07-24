package mutsa.hackathon.match.controller;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.match.service.PayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pay")
public class PayController {
    private final PayService payService;

    @GetMapping("/calculate")
    public ResponseEntity<Map<Long, Integer>> calculate(
            @RequestParam Long matchId,
            @RequestParam double totalFare) {
        return ResponseEntity.ok(payService.calculateEachPayment(matchId, totalFare));
    }

}
