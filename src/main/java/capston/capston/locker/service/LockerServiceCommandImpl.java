package capston.capston.locker.service;

import capston.capston.locker.dto.lockerCreateDTO.LockerCreateRequestDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateResponseDTO;

public interface LockerServiceCommandImpl {

    LockerCreateResponseDTO createLocker(LockerCreateRequestDTO lockerCreateRequestDTO);
}
