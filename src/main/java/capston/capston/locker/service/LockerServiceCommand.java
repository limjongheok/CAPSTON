package capston.capston.locker.service;

import capston.capston.locker.dto.lockerCreateDTO.LockerCreateRequestDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateResponseDTO;
import capston.capston.locker.model.Locker;
import capston.capston.locker.repository.LockerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LockerServiceCommand implements LockerServiceCommandImpl, LockerServiceQueryImpl{

    private final LockerRepository lockerRepository;

    @Override
    public void save(Locker locker) {
        lockerRepository.save(locker);
    }

    @Override
    public LockerCreateResponseDTO createLocker(LockerCreateRequestDTO lockerCreateRequestDTO) {
        Locker locker = lockerCreateRequestDTO.toCreateLocker();
        save(locker);
        return LockerCreateResponseDTO.toLockerCreateResponseDTO(locker);
    }
}
