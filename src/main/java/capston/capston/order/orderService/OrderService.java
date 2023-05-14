package capston.capston.order.orderService;

import capston.capston.Error.CustomException;
import capston.capston.Error.ErrorCode;
import capston.capston.order.model.Order;
import capston.capston.order.orderCreateDTO.OrderCreateResponseDTO;
import capston.capston.order.orderRepository.OrderRepository;
import capston.capston.saleProduct.model.SaleProduct;
import capston.capston.saleProduct.service.SaleProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService implements OrderServiceQueryImpl, OrderServiceCommandImpl {
    private final OrderRepository orderRepository;
    private final SaleProductService productService;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }


    @Override
    public OrderCreateResponseDTO createOrder(long productId) {
        SaleProduct product = productService.findById(productId);

        if(!product.isOfferState()){
            throw  new CustomException(ErrorCode.BadNonConfirmationException);
        }
        Order order = new Order(product);
        product.order(order);
        save(order);
        log.info(order.getSaleProduct().getSaleProductName());
        return OrderCreateResponseDTO.toOrderCreateResponseDTO(order);
    }
}
