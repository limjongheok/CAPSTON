package capston.capston.locker.service;

import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedRequestDTO;
import capston.capston.locker.model.Locker;

import java.util.List;

public interface LockerServiceQueryImpl {
    void save(Locker locker);

    List<Locker> getNotAssignLocker(LockerUnassignedRequestDTO lockerUnassignedRequestDTO);
    Locker findByIdLocker(long id);


}
