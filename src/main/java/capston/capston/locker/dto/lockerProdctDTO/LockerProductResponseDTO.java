package capston.capston.locker.dto.lockerProdctDTO;

import capston.capston.locker.model.Locker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LockerProductResponseDTO {
    private long id; // 사물함 아이디
    private  String purchasingUserName; // 구매 유저 이름
    private  String saleProductUserName; // 판매 유저 이름
    private  int buildingNum;
    private boolean checkProduct;
    private  boolean checkAssign;

    @Builder
    private LockerProductResponseDTO(long id, String purchasingUserName, String saleProductUserName, int buildingNum, boolean checkProduct, boolean checkAssign) {
        this.id = id;
        this.purchasingUserName = purchasingUserName;
        this.saleProductUserName = saleProductUserName;
        this.buildingNum = buildingNum;
        this.checkProduct = checkProduct;
        this.checkAssign = checkAssign;
    }

    public static LockerProductResponseDTO toLockerPutProductResponseDTO(Locker locker){
        return LockerProductResponseDTO.builder()
                .id(locker.getId())
                .purchasingUserName(locker.getPurchasingUser().getUserName())
                .saleProductUserName(locker.getSaleProduct().getUser().getUserName())
                .buildingNum(locker.getBuildingNum())
                .checkProduct(locker.isCheckProduct())
                .checkAssign(locker.isCheckAssign())
                .build();
    }
}
