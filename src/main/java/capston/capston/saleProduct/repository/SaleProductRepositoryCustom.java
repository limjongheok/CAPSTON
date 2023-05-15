package capston.capston.saleProduct.repository;

import capston.capston.saleProduct.model.SaleProduct;

import java.util.List;

public interface SaleProductRepositoryCustom {
    List<SaleProduct> findNoneOfferProduct();

}
