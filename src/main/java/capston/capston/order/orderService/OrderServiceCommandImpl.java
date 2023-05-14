package capston.capston.order.orderService;

import capston.capston.order.orderCreateDTO.OrderCreateResponseDTO;

public interface OrderServiceCommandImpl {

    OrderCreateResponseDTO createOrder(long productId);
}
