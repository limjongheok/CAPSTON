package capston.capston.saleProduct.model;


import capston.capston.jpaAuditing.BaseTimeEntity;
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


    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String saleProductName;

    @NotNull
    private int amount;

    @NotNull
    private String imgUrl;


    @Builder
    public SaleProduct(long id, User user, String title, String content, String saleProductName, int amount, String imgUrl) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.saleProductName = saleProductName;
        this.amount = amount;
        this.imgUrl = imgUrl;
    }
}
