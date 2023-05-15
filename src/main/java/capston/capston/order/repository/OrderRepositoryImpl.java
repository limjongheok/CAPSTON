package capston.capston.order.repository;

import capston.capston.order.model.Order;
import capston.capston.order.model.QOrder;
import capston.capston.user.model.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
    QOrder order = QOrder.order;

    // 내가 판매한 물건을 보고 싶음
    private BooleanExpression successSaleOrder(String studentId){
        return order.saleProduct.user.studentId.eq(studentId).and(order.orderStatus.isTrue());
    }

    private BooleanExpression successBuyOrder(String studentId){
        return  order.user.studentId.eq(studentId).and(order.orderStatus.isTrue());
    }

    @Override
    public List<Order> findMySuccessOrder(String studentId) {
        return (List<Order>) jpaQueryFactory.from(order).where(successSaleOrder(studentId)).fetch();
    }

    @Override
    public List<Order> findMyBuySuccessOrder(String studentId) {
        return (List<Order>) jpaQueryFactory.from(order).where(successBuyOrder(studentId)).fetch();
    }
}
