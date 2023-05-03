package capston.capston.saleProduct.dto.saleProductCreateDTO;

import capston.capston.saleProduct.model.SaleProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class SaleProductCreateResponseDTO {

    private  long id;

    private String title;

    private String content;

    private String saleProductName;

    private String imgUrl;
    private LocalDateTime createDate;
    private  LocalDateTime modifiedDate;

    @Builder
    private SaleProductCreateResponseDTO(long id, String title, String content, String saleProductName, String imgUrl, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.saleProductName = saleProductName;
        this.imgUrl = imgUrl;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public static  SaleProductCreateResponseDTO toSaleProductCreateResponseDTO(SaleProduct saleProduct){
        return SaleProductCreateResponseDTO.builder()
                .id(saleProduct.getId())
                .title(saleProduct.getTitle())
                .content(saleProduct.getContent())
                .saleProductName(saleProduct.getSaleProductName())
                .imgUrl(saleProduct.getImgUrl())
                .createDate(saleProduct.getCreateDate())
                .modifiedDate(saleProduct.getModifiedDate())
                .build();
    }
}
