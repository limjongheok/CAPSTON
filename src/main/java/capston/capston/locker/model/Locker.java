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
    private SaleProduct saleProduct; // 판매 유저



    @Column(nullable = false)
    private String lockerPassword;


    @Column(nullable = false)
    private  int buildingNum; // 대학 번호


    @Column(nullable = false)
    private boolean checkProduct; // 물품 여부 체크

    @Column
    private  boolean checkAssign; //  배정 여부 체크

    @Builder
    public Locker(User purchasingUser, SaleProduct saleProduct, String lockerPassword, int buildingNum, boolean checkProduct, boolean checkAssign) {
        this.purchasingUser = purchasingUser;
        this.saleProduct = saleProduct;
        this.lockerPassword = lockerPassword;
        this.buildingNum = buildingNum;
        this.checkProduct = checkProduct;
        this.checkAssign = checkAssign;
    }

    // 사물 함 배정 시키기
    public void assignLocker(User user, SaleProduct saleProduct){
        assignCheckAssign();
        assignPurchasingUser(user);
        assignSaleProduct(saleProduct);
        assignLockerPassword();
    }

    // 사물함 열렸을시
    public void putProductLocker(){
        putProduct();
    }


    //사물함잠겼을시
    public void pushProductLocker(){
        pushProduct();
        unAssignCheck();
        randomLockerPassword();
    }


    private  void randomLockerPassword(){
        this.lockerPassword = String.valueOf((int)(Math.random()*9999)+1000);
    }

    // 배정하기
    private void assignCheckAssign(){
        this.checkAssign = true;
    }

    // 배정 해제하기
    private void unAssignCheck(){this.checkAssign = false;}

    // 구매 유저 넣기
    private void assignPurchasingUser(User user){
        this.purchasingUser = user;
    }

    // 판매
    private  void assignSaleProduct(SaleProduct saleProduct){
        this.saleProduct = saleProduct;
    }

    private  void assignLockerPassword(){
        this.lockerPassword = String.valueOf((int)(Math.random()*9999)+1000);
    }

    private  void putProduct(){
        this.checkProduct = true;
    }

    private void pushProduct(){this.checkProduct = false;}
}
