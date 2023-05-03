package capston.capston.saleProduct.service;

import capston.capston.saleProduct.dto.saleProductFindId.SaleProductFindIdResponseDTO;
import capston.capston.saleProduct.model.SaleProduct;
import capston.capston.user.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SaleProductQueryServiceImpl {

    void save(SaleProduct saleProduct);


    List<SaleProduct> findMyProductAll(User user);

    List<SaleProduct> findAll();

    SaleProduct findById(long id);
}
