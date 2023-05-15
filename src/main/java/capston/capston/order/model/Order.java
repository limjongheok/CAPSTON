package capston.capston.order.model;


import capston.capston.jpaAuditing.BaseTimeEntity;
import capston.capston.saleProduct.model.SaleProduct;
import capston.capston.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "ProductOrder")
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Order_ID")
    private  long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SaleProduct_ID")
    private SaleProduct saleProduct;

    @Column(nullable = false)
    private boolean orderStatus;

    public Order(SaleProduct saleProduct, User user) {
        this.saleProduct = saleProduct;
        this.user = user;
        this.orderStatus = false;
    }

    public void successOrder(){
        success();
    }

    private void success(){
        this.orderStatus = true;
    }
}
