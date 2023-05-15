package capston.capston.order.service;

import capston.capston.order.dto.OrderCreateDTO.OrderCreateResponseDTO;
import capston.capston.order.dto.orderfindDTO.OrderResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderServiceCommandImpl {

    OrderCreateResponseDTO createOrder(long productId,Authentication authentication);

    List<OrderResponseDTO> orderSaleResponseDTOS(Authentication authentication);
    List<OrderResponseDTO> orderBuyResponseDTOS(Authentication authentication);

}
