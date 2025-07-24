package mutsa.hackathon.dto.request;

import lombok.Getter;

@Getter
public class UserRequest {

    private String name;
    private String email;
    private String destination;
    private String gender;     // 'MALE' 또는 'FEMALE' 문자열로 받음
    private String birth;      // ISO 형식 문자열 "YYYY-MM-DD"
    private String rate;       // 문자열로 받아서 서버에서 BigDecimal로 파싱
    private int coin;

}
