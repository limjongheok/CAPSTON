package capston.capston.locker.dto.lockerAssignDTO;

import capston.capston.locker.model.Locker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LockerAssignResponseDTO {


    private long id; // 사물함 아이디
    private  String purchasingUserName; // 구매 유저 이름
    private  String saleProductUserName; // 판매 유저 이름
    private  int buildingNum;

    private boolean checkProduct;
    private  boolean checkAssign;
    private  String lockerPassword;

    @Builder
    private LockerAssignResponseDTO(long id, String purchasingUserName, String saleProductUserName, int buildingNum, boolean checkProduct, boolean checkAssign, String lockerPassword) {
        this.id = id;
        this.purchasingUserName = purchasingUserName;
        this.saleProductUserName = saleProductUserName;
        this.buildingNum = buildingNum;;
        this.checkProduct = checkProduct;
        this.checkAssign = checkAssign;
        this.lockerPassword = lockerPassword;
        ;
    }

    public static LockerAssignResponseDTO toLockerAssignResponseDTO(Locker locker){
        return LockerAssignResponseDTO.builder()
                .id(locker.getId())
                .purchasingUserName(locker.getPurchasingUser().getUserName())
                .saleProductUserName(locker.getSaleProduct().getUser().getUserName())
                .buildingNum(locker.getBuildingNum())
                .checkProduct(locker.isCheckProduct())
                .checkAssign(locker.isCheckAssign())
                .lockerPassword(locker.getLockerPassword())
                .build();
    }
}
