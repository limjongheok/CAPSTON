package capston.capston.order.model;


import capston.capston.jpaAuditing.BaseTimeEntity;
import capston.capston.saleProduct.model.SaleProduct;
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


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SaleProduct_ID")
    private SaleProduct saleProduct;

    public Order(SaleProduct saleProduct) {
        this.saleProduct = saleProduct;
    }
}
