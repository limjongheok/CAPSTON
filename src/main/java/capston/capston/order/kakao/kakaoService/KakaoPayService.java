package capston.capston.order.kakao.kakaoService;

import capston.capston.Error.CustomException;
import capston.capston.Error.ErrorCode;
import capston.capston.order.kakao.kakaoDTO.KakaoApproveResponseDTO;
import capston.capston.order.kakao.kakaoDTO.KakaoReadyResponseDTO;
import capston.capston.order.model.Order;
import capston.capston.order.service.OrderService;
import capston.capston.saleProduct.service.SaleProductService;
import capston.capston.user.model.User;
import capston.capston.user.service.UserService;
import kotlin.jvm.internal.PackageReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

    private static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
    private final SaleProductService saleProductService;
    private  final UserService userService;
    private final OrderService orderService;

    @Value("${kakao.adminKey}")
    private  String adminKey;


    private KakaoReadyResponseDTO kakaoReady;



    public KakaoReadyResponseDTO kakaoPayReady(long productId) {
        Order order = saleProductService.findById(productId).getOrder();

        if(order.isOrderStatus()){
            throw new CustomException(ErrorCode.BadSuccessOrderException);
        }


        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", String.valueOf(order.getId()));
        parameters.add("partner_user_id", order.getSaleProduct().getUser().getUser());
        parameters.add("item_name", order.getSaleProduct().getSaleProductName());
        parameters.add("quantity", "1");
        parameters.add("total_amount", String.valueOf(order.getSaleProduct().getOfferPrice()));
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://59.26.59.60:8080/api/payment_inprogess?productId="+productId); // 성공 시 redirect url
        parameters.add("cancel_url", "http://localhost:8080/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "http://localhost:8080/payment/fail"); // 실패 시 redirect url

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(adminKey);

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponseDTO.class);

        return kakaoReady != null ? kakaoReady : null;
    }




    public KakaoApproveResponseDTO approveResponse(long productId, String pgToken) {

        Order order = saleProductService.findById(productId).getOrder();
        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id",  String.valueOf(order.getId()));
        parameters.add("partner_user_id", order.getSaleProduct().getUser().getUser());
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponseDTO approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponseDTO.class);


        order.successOrder();
        orderService.save(order);
        User saleUser = order.getSaleProduct().getUser();
        saleUser.saleUserPoint(approveResponse.getAmount().getTotal());
        userService.save(saleUser);

        return approveResponse;
    }

    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + adminKey;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
}
