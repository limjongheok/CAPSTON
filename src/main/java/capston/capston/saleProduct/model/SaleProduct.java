package capston.capston.saleProduct.model;


import capston.capston.jpaAuditing.BaseTimeEntity;
import capston.capston.order.model.Order;
import capston.capston.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
public class SaleProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="SaleProduct_ID")
    private  long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User user;

    @OneToOne(mappedBy = "saleProduct")
    private Order order;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String saleProductName;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private  boolean offerState;

    @Column(nullable = true)
    private long offerPrice;

    @Column(nullable = true)
    private String offerStudentID;




    @Builder
    public SaleProduct(long id, User user, String title, String content, String saleProductName, int amount, String imgUrl,boolean offerState, long offerPrice, String offerStudentID) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.saleProductName = saleProductName;
        this.amount = amount;
        this.imgUrl = imgUrl;
        this.offerState = offerState;
        this.offerPrice = offerPrice;
        this.offerStudentID = offerStudentID;
    }
    public void confirmationProduct(long offerPrice, String offerStudentID){
        confirmation(offerPrice,offerStudentID);
    }

    public void order(Order order){
        orderProduct(order);
    }

    private  void confirmation(long offerPrice, String offerStudentID){
        this.offerState = true;
        this.offerStudentID = offerStudentID;
        this.offerPrice = offerPrice;
    }

    private void orderProduct(Order order){
        this.order = order;
    }
}
