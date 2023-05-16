package capston.capston.locker.service;

import capston.capston.locker.dto.lockerAssignDTO.LockerAssignResponseDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateRequestDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateResponseDTO;
import capston.capston.locker.dto.lockerPasswordCheckDTO.LockerPasswordCheckResponseDTO;
import capston.capston.locker.dto.lockerProdctDTO.LockerProductResponseDTO;
import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedRequestDTO;
import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface LockerServiceCommandImpl {

    LockerCreateResponseDTO createLocker(LockerCreateRequestDTO lockerCreateRequestDTO);

    List<LockerUnassignedResponseDTO> unassignedLocker(LockerUnassignedRequestDTO lockerUnassignedRequestDTO);
    LockerAssignResponseDTO assignLocker(String studentId, long productId, long lockerId, Authentication authentication);

    LockerPasswordCheckResponseDTO buyerCheckPassword(long lockerId, String lockerPassword);
    LockerPasswordCheckResponseDTO saleCheckPassword(long lockerId, String lockerPassword);
    LockerProductResponseDTO putProduct(long lockerId);
    LockerProductResponseDTO pushProduct(long lockerId);
}
