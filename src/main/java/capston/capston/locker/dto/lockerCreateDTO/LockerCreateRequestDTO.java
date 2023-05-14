package capston.capston.locker.dto.lockerCreateDTO;

import capston.capston.locker.model.Locker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.util.Random;

@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class LockerCreateRequestDTO {
    @NotBlank
    private  int buildingNum;

    public Locker toCreateLocker(){

        return Locker.builder()
                .lockerPassword(String.valueOf((int)(Math.random()*9999)+1000))
                .buildingNum(this.buildingNum)
                .checkProduct(false)
                .checkAssign(false)
                .build();
    }


}
