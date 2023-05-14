package capston.capston.order.orderCreateDTO;

import capston.capston.order.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderCreateResponseDTO {
    private  long id;

    private String title;

    private String content;

    private String saleProductName;

    private  long offerPrice;
    private LocalDateTime createDate;
    private  LocalDateTime modifiedDate;

    private String offerStudentId;

    @Builder

    private OrderCreateResponseDTO(long id, String title, String content, String saleProductName, long offerPrice, LocalDateTime createDate, LocalDateTime modifiedDate, String offerStudentId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.saleProductName = saleProductName;
        this.offerPrice = offerPrice;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.offerStudentId = offerStudentId;
    }

    public static OrderCreateResponseDTO toOrderCreateResponseDTO(Order order){
        return OrderCreateResponseDTO.builder()
                .id(order.getId())
                .title(order.getSaleProduct().getTitle())
                .content(order.getSaleProduct().getContent())
                .saleProductName(order.getSaleProduct().getSaleProductName())
                .offerPrice(order.getSaleProduct().getOfferPrice())
                .createDate(order.getCreateDate())
                .modifiedDate(order.getModifiedDate())
                .offerStudentId(order.getSaleProduct().getOfferStudentID())
                .build();

    }
}
