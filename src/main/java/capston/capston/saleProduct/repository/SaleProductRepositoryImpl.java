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

    QSaleProduct SaleProduct = QSaleProduct.saleProduct;

    private BooleanExpression NotOfferProduct(){
        return  SaleProduct.offerState.isFalse();
    }

    @Override
    public List<SaleProduct> findNoneOfferProduct() {
        return (List<SaleProduct>) jpaQueryFactory.from(SaleProduct).where(NotOfferProduct()).fetch();
    }
}
