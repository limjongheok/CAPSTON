package capston.capston.locker.repository;

import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedRequestDTO;
import capston.capston.locker.model.Locker;

import java.util.List;

public interface LockerRepositoryCustom {
    List<Locker> getNotAssignLocker(int buildingNum);
}
