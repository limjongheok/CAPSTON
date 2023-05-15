package capston.capston.order.service;

import capston.capston.order.model.Order;

import java.util.List;

public interface OrderServiceQueryImpl {
    void save(Order order);
    List<Order> findSuccessSaleOrder(String studentId);

    List<Order> findSuccessBuyOrder(String studentId);
}
