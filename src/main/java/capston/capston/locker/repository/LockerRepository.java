package capston.capston.locker.repository;

import capston.capston.locker.model.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LockerRepository extends JpaRepository<Locker, Long> {
    List<Locker> findAllByCheckAssignEquals();
    
}
