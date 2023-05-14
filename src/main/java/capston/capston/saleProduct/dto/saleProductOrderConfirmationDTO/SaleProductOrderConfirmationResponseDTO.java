package capston.capston.saleProduct.dto.saleProductOrderConfirmationDTO;

import capston.capston.saleProduct.model.SaleProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SaleProductOrderConfirmationResponseDTO {

    private  long id;

    private  String title;
    private String content;
    private String saleProductName;
    private  boolean offerState;
    private  long offerPrice;
    private String offerStudentId;


    @Builder
    private SaleProductOrderConfirmationResponseDTO(long id, String title, String content, String saleProductName, boolean offerState, long offerPrice, String offerStudentId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.saleProductName = saleProductName;
        this.offerState = offerState;
        this.offerPrice = offerPrice;
        this.offerStudentId = offerStudentId;
    }

    public static SaleProductOrderConfirmationResponseDTO toSaleProductOrderConfirmationResponseDTO(SaleProduct saleProduct){
        return SaleProductOrderConfirmationResponseDTO.builder()
                .id(saleProduct.getId())
                .title(saleProduct.getTitle())
                .content(saleProduct.getContent())
                .saleProductName(saleProduct.getSaleProductName())
                .offerState(saleProduct.isOfferState())
                .offerPrice(saleProduct.getOfferPrice())
                .offerStudentId(saleProduct.getOfferStudentID())
                .build();
    }
}
