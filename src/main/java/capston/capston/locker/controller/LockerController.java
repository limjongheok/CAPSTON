package capston.capston.locker.controller;

import capston.capston.locker.dto.lockerCreateDTO.LockerCreateRequestDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateResponseDTO;
import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedResponseDTO;
import capston.capston.locker.service.LockerServiceCommandImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LockerController {
    private final LockerServiceCommandImpl lockerService;


    // 사물함 생성
    @PostMapping("/api/locker/create")
    public ResponseEntity<?> createLocker(@RequestBody LockerCreateRequestDTO lockerCreateRequestDTO){

        LockerCreateResponseDTO lockerCreateResponseDTO = lockerService.createLocker(lockerCreateRequestDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("msg", "사물함 생성에 성공하였습니다.");
        response.put("data", lockerCreateResponseDTO);
        return ResponseEntity.ok().body(response);
    }

    // 배정 받지 않은 사물함 조회
    @GetMapping("/api/web/locker/unassigned")
    public  ResponseEntity<?> unassignedLocker(){
        LockerUnassignedResponseDTO lockerUnassignedResponseDTO = lockerService.
    }

}
