package capston.capston.saleProduct.dto.saleProductFindId;

import capston.capston.saleProduct.dto.saleProductFindmyDTO.SaleProductFindMyResponseDTO;
import capston.capston.saleProduct.model.SaleProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class SaleProductFindIdResponseDTO {
    private  long id;

    private String title;

    private String content;

    private String saleProductName;

    private String imgUrl;
    private LocalDateTime createDate;
    private  LocalDateTime modifiedDate;

    @Builder
    private SaleProductFindIdResponseDTO(long id, String title, String content, String saleProductName, String imgUrl, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.saleProductName = saleProductName;
        this.imgUrl = imgUrl;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    public static SaleProductFindIdResponseDTO toSaleProductFindIdResponseDTO(SaleProduct saleProduct){
        return SaleProductFindIdResponseDTO.builder()
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
