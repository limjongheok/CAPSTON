package capston.capston.locker.dto.lockerCreateDTO;

import capston.capston.locker.model.Locker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LockerCreateResponseDTO {

    private  int buildingNum;
    private boolean checkDoor;
    private boolean checkProduct;
    private  boolean checkAssign;

    @Builder

    private LockerCreateResponseDTO(int buildingNum, boolean checkDoor, boolean checkProduct, boolean checkAssign) {
        this.buildingNum = buildingNum;
        this.checkDoor = checkDoor;
        this.checkProduct = checkProduct;
        this.checkAssign = checkAssign;
    }

    public static LockerCreateResponseDTO toLockerCreateResponseDTO(Locker locker){
        return  LockerCreateResponseDTO
                .builder()
                .buildingNum(locker.getBuildingNum())
                .checkDoor(locker.isCheckDoor())
                .checkProduct(locker.isCheckProduct())
                .checkAssign(locker.isCheckAssign())
                .build();
    }
}
