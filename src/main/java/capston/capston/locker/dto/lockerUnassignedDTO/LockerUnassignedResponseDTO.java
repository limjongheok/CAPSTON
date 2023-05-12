package capston.capston.locker.dto.lockerUnassignedDTO;

import capston.capston.locker.model.Locker;
import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LockerUnassignedResponseDTO {
    private  long id;
    private int buildingNum;

    public static LockerUnassignedResponseDTO toLockerUnassignedResponseDTO(Locker locker){
        return new LockerUnassignedResponseDTO(locker.getId(),locker.getBuildingNum());
    }
}
