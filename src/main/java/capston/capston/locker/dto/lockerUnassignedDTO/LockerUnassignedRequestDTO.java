package capston.capston.locker.dto.lockerUnassignedDTO;

import capston.capston.locker.model.Locker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LockerUnassignedRequestDTO {
    @NotBlank
    private  int buildingNum;

}
