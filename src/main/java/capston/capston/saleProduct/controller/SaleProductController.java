package capston.capston.saleProduct.controller;

import capston.capston.saleProduct.dto.saleProductCreateDTO.SaleProductCreateRequestDTO;
import capston.capston.saleProduct.dto.saleProductCreateDTO.SaleProductCreateResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindAll.SaleProductFindAllResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindId.SaleProductFindIdResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindmyDTO.SaleProductFindMyResponseDTO;
import capston.capston.saleProduct.dto.saleProductOrderConfirmationDTO.SaleProductOrderConfirmationRequestDTO;
import capston.capston.saleProduct.dto.saleProductOrderConfirmationDTO.SaleProductOrderConfirmationResponseDTO;
import capston.capston.saleProduct.service.SaleProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SaleProductController {

    private final SaleProductService saleProductService;


    // 상품 등록
    @PostMapping("/api/saleproduct/create")

    public ResponseEntity<?> createSaleProduct(Authentication authentication, @RequestBody SaleProductCreateRequestDTO saleProductCreateRequestDTO){
        SaleProductCreateResponseDTO saleProductCreateResponseDTO = saleProductService.createSaleProduct(authentication,saleProductCreateRequestDTO);
        return ResponseEntity.ok().body(createResponse(saleProductCreateResponseDTO,"상품 생성에성공하였습니다."));
    }

    // 내가 올린 상품 전체 조회
    @GetMapping("/api/sale/product/find/my/product")
    public ResponseEntity<?> findMyProduct(Authentication authentication){
        List<SaleProductFindMyResponseDTO> saleProductFindMyResponseDTOS = saleProductService.findMyProduct(authentication);
        return ResponseEntity.ok().body(createResponse(saleProductFindMyResponseDTOS,"내가 올린 상품 전체 조회에 성공하였습니다."));
    }


    // 상품 전체 조회
    @GetMapping("/api/saleproduct/find/all")
    public ResponseEntity<?> findAllProduct(){
        List<SaleProductFindAllResponseDTO> saleProductFindAllResponseDTOS = saleProductService.findAllProduct();
        return ResponseEntity.ok().body(createResponse(saleProductFindAllResponseDTOS,"상품 전체 조회가 성공하였습니다."));
    }

    // 상품 개별 조회
    @GetMapping("/api/saleproduct/find/{productId}")
    public ResponseEntity<?> findProductId(@PathVariable(value = "productId") int productId, Authentication authentication){
        SaleProductFindIdResponseDTO saleProductFindIdResponseDTO = saleProductService.findProductId(productId);

        return ResponseEntity.ok().body(createResponse(saleProductFindIdResponseDTO,"상품 조회가 성공하였습니다."));

    }

    // 상품 주문 확정
    @PutMapping("/api/saleproduct/order/confirmation/{buyStudentId}/{productId}")
    public ResponseEntity<?> orderConfirmation(@PathVariable(value = "buyStudentId") String buyStudentId, @PathVariable(value = "productId") long productId, @RequestBody SaleProductOrderConfirmationRequestDTO saleProductOrderConfirmationRequestDTO, Authentication authentication){

        SaleProductOrderConfirmationResponseDTO saleProductOrderConfirmationResponseDTO = saleProductService.orderConfirmation(buyStudentId,productId, saleProductOrderConfirmationRequestDTO.getOfferPrice(), authentication);

        return ResponseEntity.ok().body(createResponse(saleProductOrderConfirmationResponseDTO,"상품 주문이 확정 되었습니다."));
    }



    private Map<String,Object> createResponse(Object object, String msg){
        Map<String, Object> response = new HashMap<>();
        response.put("msg", msg);
        response.put("data", object);
        return response;
    }



}
