package capston.capston.saleProduct.service;

import capston.capston.saleProduct.dto.saleProductCreateDTO.SaleProductCreateRequestDTO;
import capston.capston.saleProduct.dto.saleProductCreateDTO.SaleProductCreateResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindAll.SaleProductFindAllResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindId.SaleProductFindIdResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindmyDTO.SaleProductFindMyResponseDTO;
import capston.capston.saleProduct.dto.saleProductOrderConfirmationDTO.SaleProductOrderConfirmationResponseDTO;
import capston.capston.saleProduct.model.SaleProduct;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SaleProductCommendServiceImpl {

    SaleProductCreateResponseDTO createSaleProduct(Authentication authentication, SaleProductCreateRequestDTO saleProductCreateRequestDTO);

    List<SaleProductFindMyResponseDTO> findMyProduct(Authentication authentication);

    List<SaleProductFindAllResponseDTO> findAllProduct();

    SaleProductFindIdResponseDTO findProductId(long id);
    SaleProductOrderConfirmationResponseDTO orderConfirmation(String buyStudentId, long productId, long offerPrice, Authentication authentication);
}
