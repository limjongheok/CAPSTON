package capston.capston.order.dto.orderfindDTO;

import capston.capston.order.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderResponseDTO {

    private  long id;

    private String title;

    private String content;

    private String saleProductName;

    private String imgUrl;
    private  long offerPrice; // 판매 가격
    private String studentId; // 판매자 아이디
    private LocalDateTime createDate;
    private  LocalDateTime modifiedDate;

    @Builder
    private OrderResponseDTO(long id, String title, String content, String saleProductName, String imgUrl, long offerPrice, String studentId, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.saleProductName = saleProductName;
        this.imgUrl = imgUrl;
        this.offerPrice = offerPrice;
        this.studentId = studentId;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public static OrderResponseDTO toOrderSaleResponseDTO(Order order){
        return  OrderResponseDTO.builder()
                .id(order.getId())
                .title(order.getSaleProduct().getTitle())
                .content(order.getSaleProduct().getContent())
                .saleProductName(order.getSaleProduct().getSaleProductName())
                .imgUrl(order.getSaleProduct().getImgUrl())
                .offerPrice(order.getSaleProduct().getOfferPrice())
                .studentId(order.getUser().getStudentId())
                .createDate(order.getCreateDate())
                .modifiedDate(order.getModifiedDate())
                .build();

    }

    public static  OrderResponseDTO toOrderBuyerResponseDTO(Order order){
        return  OrderResponseDTO.builder()
                .id(order.getId())
                .title(order.getSaleProduct().getTitle())
                .content(order.getSaleProduct().getContent())
                .saleProductName(order.getSaleProduct().getSaleProductName())
                .imgUrl(order.getSaleProduct().getImgUrl())
                .offerPrice(order.getSaleProduct().getOfferPrice())
                .studentId(order.getSaleProduct().getUser().getStudentId())
                .createDate(order.getCreateDate())
                .modifiedDate(order.getModifiedDate())
                .build();
    }
}
