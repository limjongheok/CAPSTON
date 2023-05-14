package capston.capston.saleProduct.repository;

import capston.capston.saleProduct.model.SaleProduct;
import capston.capston.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct,Long>, SaleProductRepositoryCustom {

    @Override
    List<SaleProduct> findNoneOfferProduct();

    List<SaleProduct> findAllByUser(User user);
}
