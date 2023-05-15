package capston.capston.order.repository;

import capston.capston.order.model.Order;
import capston.capston.user.model.User;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findMySuccessOrder(String studentId);
    List<Order> findMyBuySuccessOrder(String studentId);
}
