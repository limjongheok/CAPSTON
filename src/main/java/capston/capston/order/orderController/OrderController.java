package capston.capston.order.orderController;

import capston.capston.order.kakao.kakaoDTO.KakaoApproveResponseDTO;
import capston.capston.order.kakao.kakaoService.KakaoPayService;
import capston.capston.order.orderCreateDTO.OrderCreateResponseDTO;
import capston.capston.order.orderService.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final KakaoPayService kakaoPayService;
    private final OrderService orderService;

    // 주문 생성하기
    @PostMapping("/api/order/create/{productId}")
    public ResponseEntity<?> orderCreate(@PathVariable(value = "productId") long productId){
        OrderCreateResponseDTO orderCreateResponseDTO = orderService.createOrder(productId);

        return ResponseEntity.ok().body(createResponse(orderCreateResponseDTO,"주문생성이 완료되었습니다."));

    }



    @GetMapping("/api/kakao/ready/{productId}")
    public ResponseEntity readyToKakaoPay(@PathVariable (value = "productId") long productId) {
        String url = kakaoPayService.kakaoPayReady(productId);
        return ResponseEntity.ok().body(url);
    }

    @GetMapping("/api/kakao/{productId}/success")
    public ResponseEntity afterPayRequest(@PathVariable(value = "productId") long productId,@RequestParam("pg_token") String pgToken) {

        System.out.println(pgToken);
        KakaoApproveResponseDTO kakaoApprove = kakaoPayService.approveResponse(productId,pgToken);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    private Map<String,Object> createResponse(Object object, String msg){
        Map<String, Object> response = new HashMap<>();
        response.put("msg", msg);
        response.put("data", object);
        return response;
    }


}
