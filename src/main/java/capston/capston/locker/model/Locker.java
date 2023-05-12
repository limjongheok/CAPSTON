package capston.capston.locker.model;

import capston.capston.jpaAuditing.BaseTimeEntity;
import capston.capston.saleProduct.model.SaleProduct;
import capston.capston.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@NoArgsConstructor
@Getter

public class Locker extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Locker_ID")
    private  long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User purchasingUser; // 구매 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SaleProduct_ID")
    private SaleProduct saleProduct; // 구매 유저



    @Column(nullable = false)
    private String lockerPassword;


    @Column(nullable = false)
    private  int buildingNum; // 대학 번호

    @Column(nullable = false)
    private boolean checkDoor; // 문잠김 체크

    @Column(nullable = false)
    private boolean checkProduct; // 물품 여부 체크

    @Column
    private  boolean checkAssign; //  배정 여부 체크

    @Builder
    public Locker(User purchasingUser, SaleProduct saleProduct, String lockerPassword, int buildingNum, boolean checkDoor, boolean checkProduct, boolean checkAssign) {
        this.purchasingUser = purchasingUser;
        this.saleProduct = saleProduct;
        this.lockerPassword = lockerPassword;
        this.buildingNum = buildingNum;
        this.checkDoor = checkDoor;
        this.checkProduct = checkProduct;
        this.checkAssign = checkAssign;
    }
}
