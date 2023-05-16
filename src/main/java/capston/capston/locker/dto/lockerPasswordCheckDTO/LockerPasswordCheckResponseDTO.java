package capston.capston.locker.dto.lockerPasswordCheckDTO;

import capston.capston.locker.dto.lockerAssignDTO.LockerAssignResponseDTO;
import capston.capston.locker.model.Locker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LockerPasswordCheckResponseDTO {
    private long productId; // 사물함 아이디
    private  String purchasingUserName; // 구매 유저 이름
    private  String saleProductUserName; // 판매 유저 이름
    private  int buildingNum;
    private boolean checkProduct;
    private  boolean checkAssign;
    private  String lockerPassword;

    @Builder
    private LockerPasswordCheckResponseDTO(long productId, String purchasingUserName, String saleProductUserName, int buildingNum, boolean checkDoor, boolean checkProduct, boolean checkAssign, String lockerPassword) {
        this.productId = productId;
        this.purchasingUserName = purchasingUserName;
        this.saleProductUserName = saleProductUserName;
        this.buildingNum = buildingNum;
        this.checkProduct = checkProduct;
        this.checkAssign = checkAssign;
        this.lockerPassword = lockerPassword;
        ;
    }

    public static LockerPasswordCheckResponseDTO toLockerPasswordCheckResponseDTO(Locker locker){
        return LockerPasswordCheckResponseDTO.builder()
                .productId(locker.getSaleProduct().getId())
                .purchasingUserName(locker.getPurchasingUser().getUserName())
                .saleProductUserName(locker.getSaleProduct().getUser().getUserName())
                .buildingNum(locker.getBuildingNum())
                .checkProduct(locker.isCheckProduct())
                .checkAssign(locker.isCheckAssign())
                .lockerPassword(locker.getLockerPassword())
                .build();
    }
}
