package capston.capston.order.controller;

import capston.capston.order.dto.orderfindDTO.OrderResponseDTO;
import capston.capston.order.kakao.kakaoDTO.KakaoApproveResponseDTO;
import capston.capston.order.kakao.kakaoDTO.KakaoReadyResponseDTO;
import capston.capston.order.kakao.kakaoService.KakaoPayService;
import capston.capston.order.dto.OrderCreateDTO.OrderCreateResponseDTO;
import capston.capston.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final KakaoPayService kakaoPayService;
    private final OrderService orderService;

    // 주문 생성하기
    @PostMapping("/api/order/create/{productId}")
    public ResponseEntity<?> orderCreate(@PathVariable(value = "productId") long productId, Authentication authentication){
        OrderCreateResponseDTO orderCreateResponseDTO = orderService.createOrder(productId, authentication);

        return ResponseEntity.ok().body(createResponse(orderCreateResponseDTO,"주문생성이 완료되었습니다."));

    }



    // 카카오 주문 준비
    @GetMapping("/api/kakao/ready/{productId}")
    public ResponseEntity<?> readyToKakaoPay(@PathVariable (value = "productId") long productId) {
        KakaoReadyResponseDTO kakaoReadyResponseDTO = kakaoPayService.kakaoPayReady(productId);
        return ResponseEntity.ok().body(createResponse(kakaoReadyResponseDTO,"카카오페이 준비 완료"));
    }

    // 카카오 주문 성공

    // 카카오 주문 성공
    @GetMapping("/api/kakao/success/{productId}")
    public ResponseEntity afterPayRequest(@PathVariable(value = "productId") long productId,@RequestParam("pg_token") String pgToken) {

        System.out.println(pgToken);
        KakaoApproveResponseDTO kakaoApprove = kakaoPayService.approveResponse(productId,pgToken);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }


    // 내 판만 성공 조회

    @GetMapping("/api/order/sale")
    public  ResponseEntity<?> saleOrder(Authentication authentication){

        List<OrderResponseDTO> orderResponseDTOS = orderService.orderSaleResponseDTOS(authentication);
        return ResponseEntity.ok().body(createResponse(orderResponseDTOS,"판매 물품 성공 조회에 성공하였습니다."));


    }

    // 내 구매 조회
    @GetMapping("/api/order/buy")
    public ResponseEntity<?> buyOrder(Authentication authentication){
        List<OrderResponseDTO> orderResponseDTOS = orderService.orderBuyResponseDTOS(authentication);


        return ResponseEntity.ok().body(createResponse(orderResponseDTOS,"구매 물품 성공 조회에 성공하였습니다."));
    }
    @GetMapping("/api/redirect")
    public ResponseEntity<?> redirect() throws URISyntaxException {
        URI redirectUri  =new URI("http://loaclhost:80/");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirectUri);
        return  new ResponseEntity<>(redirectUri, HttpStatus.OK);
    }
    private Map<String,Object> createResponse(Object object, String msg){
        Map<String, Object> response = new HashMap<>();
        response.put("msg", msg);
        response.put("data", object);
        return response;
    }


}
