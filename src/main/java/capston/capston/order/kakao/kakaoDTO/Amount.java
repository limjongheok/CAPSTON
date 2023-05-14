package capston.capston.order.kakao.kakaoDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Amount {
    private int total; // 총 결제 금액
    private int tax_free; // 비과세 금액
    private int tax; // 부가세 금액
    private int point; // 사용한 포인트
    private int discount; // 할인금액
    private int green_deposit; // 컵 보증금
}
