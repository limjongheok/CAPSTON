package capston.capston.saleProduct.repository;

import capston.capston.saleProduct.model.QSaleProduct;
import capston.capston.saleProduct.model.SaleProduct;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class SaleProductRepositoryImpl implements SaleProductRepositoryCustom {
    private  final JPAQueryFactory jpaQueryFactory;

    public SaleProductRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QSaleProduct saleProduct = QSaleProduct.saleProduct;

    private BooleanExpression notOfferProduct(){
        return  saleProduct.offerState.isFalse();
    }

    @Override
    public List<SaleProduct> findNoneOfferProduct() {
        return (List<SaleProduct>) jpaQueryFactory.from(saleProduct).where(notOfferProduct()).fetch();
    }
}
