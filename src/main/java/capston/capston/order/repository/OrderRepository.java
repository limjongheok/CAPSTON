package capston.capston.order.repository;

import capston.capston.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    @Override
    List<Order> findMySuccessOrder(String studentId);

    @Override
    List<Order> findMyBuySuccessOrder(String studentId);
}
