package capston.capston.locker.controller;

import capston.capston.locker.dto.lockerAssignDTO.LockerAssignResponseDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateRequestDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateResponseDTO;
import capston.capston.locker.dto.lockerPasswordCheckDTO.LockerPasswordCheckResponseDTO;
import capston.capston.locker.dto.lockerProdctDTO.LockerProductResponseDTO;
import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedRequestDTO;
import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedResponseDTO;
import capston.capston.locker.service.LockerService;
import capston.capston.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LockerController {
    private final LockerService lockerService;
    private final MessageService messageService;


    // 사물함 생성
    @PostMapping("/api/locker/create")
    public ResponseEntity<?> createLocker(@RequestBody LockerCreateRequestDTO lockerCreateRequestDTO){
        LockerCreateResponseDTO lockerCreateResponseDTO = lockerService.createLocker(lockerCreateRequestDTO);
        return ResponseEntity.ok().body(createResponse(lockerCreateResponseDTO, "사물함 생성에 성공하였습니다."));
    }


    // 배정 받지 않은 사물함 조회
    @PostMapping("/api/web/locker/unassigned")
    public  ResponseEntity<?> unassignedLocker(Authentication authentication, @RequestBody LockerUnassignedRequestDTO lockerUnassignedRequestDTO){
        List<LockerUnassignedResponseDTO> lockerUnassignedResponseDTOS = lockerService.unassignedLocker(lockerUnassignedRequestDTO);
        return ResponseEntity.ok().body(createResponse(lockerUnassignedResponseDTOS,"배정받지 않은 사물함 검색에 성공하였습니다."));
    }

    // 사물함 배정
    @GetMapping("/api/web/locker/assign/{buyStudentId}/{productId}/{lockerId}")
    public ResponseEntity<?> assignLocker(@PathVariable(value = "buyStudentId") String buyStudentId, @PathVariable(value = "productId") long productId, @PathVariable(value = "lockerId") long lockerId, Authentication authentication){

        LockerAssignResponseDTO lockerAssignResponseDTO = lockerService.assignLocker(buyStudentId,productId,lockerId,authentication);
        messageService.sendAssignBuyerLocker(buyStudentId,lockerId);
        messageService.sendAssignSaleLocker(productId,lockerId);
        return ResponseEntity.ok().body(createResponse(lockerAssignResponseDTO,"사물함 배정에 성공하였습니다."));
    }


    // 판매자 비번 검사 하여 문열기
    @GetMapping("/api/locker/sale/password/check/{lockerId}/{password}")
    public ResponseEntity<?> saleCheckPassword(@PathVariable(value = "lockerId")long lockerId, @PathVariable(value = "password") String lockerPassword){
        LockerPasswordCheckResponseDTO lockerPasswordCheckResponseDTO = lockerService.saleCheckPassword(lockerId,lockerPassword);
        return ResponseEntity.ok().body(createResponse(lockerPasswordCheckResponseDTO,"판매자가 문열기를 성공하였습니다."));
    }

    // 구매자 비번 검사하여 문열기
    @GetMapping("/api/locker/buyer/password/check/{lockerId}/{password}")
    public ResponseEntity<?> buyerCheckPassword(@PathVariable(value = "lockerId")long lockerId, @PathVariable(value = "password") String lockerPassword){
        LockerPasswordCheckResponseDTO lockerPasswordCheckResponseDTO = lockerService.buyerCheckPassword(lockerId,lockerPassword);
        return ResponseEntity.ok().body(createResponse(lockerPasswordCheckResponseDTO,"구매자가 문열기를 성공하였습니다."));

    }

    // 물건 넣기
    @GetMapping("/api/locker/sale/product/{lockerId}")
    public ResponseEntity<?> putProductLocker(@PathVariable(value = "lockerId") long lockerId){
        LockerProductResponseDTO lockerProductResponseDTO = lockerService.putProduct(lockerId);
        messageService.sendPutProductLocker(lockerId);
        return ResponseEntity.ok().body(createResponse(lockerProductResponseDTO,"물건 넣기가 성공하였습니다."));
    }

    //물건 빼기
    @GetMapping("/api/locker/buyer/product/{lockerId}")
    public ResponseEntity<?> pushProductLocker(@PathVariable(value = "lockerId") long lockerId){
        LockerProductResponseDTO lockerProductResponseDTO = lockerService.pushProduct(lockerId);
        messageService.sendPushProductLocker(lockerId);
        return ResponseEntity.ok().body(createResponse(lockerProductResponseDTO,"물건 빼기가 성공 하였습니다."));
    }


    private Map<String,Object> createResponse(Object object, String msg){
        Map<String, Object> response = new HashMap<>();
        response.put("msg", msg);
        response.put("data", object);
        return response;
    }









}