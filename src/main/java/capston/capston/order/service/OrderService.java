package capston.capston.order.service;

import capston.capston.Error.CustomException;
import capston.capston.Error.ErrorCode;
import capston.capston.auth.PrincipalDetails;
import capston.capston.order.dto.orderfindDTO.OrderResponseDTO;
import capston.capston.order.model.Order;
import capston.capston.order.dto.OrderCreateDTO.OrderCreateResponseDTO;
import capston.capston.order.repository.OrderRepository;
import capston.capston.saleProduct.model.SaleProduct;
import capston.capston.saleProduct.service.SaleProductService;
import capston.capston.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public List<Order> findSuccessSaleOrder(String studentId) {
        return orderRepository.findMySuccessOrder(studentId);
    }

    @Override
    public List<Order> findSuccessBuyOrder(String studentId) {
        return orderRepository.findMyBuySuccessOrder(studentId);
    }


    @Override
    public OrderCreateResponseDTO createOrder(long productId, Authentication authentication) {
        SaleProduct product = productService.findById(productId);
        User user = ((PrincipalDetails)authentication.getPrincipal()).getUser(); // 현재 로그인 유저

        if(!product.isOfferState()){
            throw  new CustomException(ErrorCode.BadNonConfirmationException);
        }
        if(product.isOrderStatus()){
            throw  new CustomException(ErrorCode.BadSuccessOrderException);
        }

        Order order = new Order(product,user);
        save(order);
        product.order(order);
        product.orderSuccessProduct();
        productService.save(product);

        log.info(order.getSaleProduct().getSaleProductName());
        return OrderCreateResponseDTO.toOrderCreateResponseDTO(order);
    }

    @Override
    public List<OrderResponseDTO> orderSaleResponseDTOS(Authentication authentication) {
        User user = ((PrincipalDetails)authentication.getPrincipal()).getUser(); // 현재 로그인 유저
        List<Order> orders = findSuccessSaleOrder(user.getStudentId()); // 현재 로그인 유저 판매 성공 물품
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for(Order order : orders){
            orderResponseDTOS.add(OrderResponseDTO.toOrderSaleResponseDTO(order));
        }
        return orderResponseDTOS;
    }

    @Override
    public List<OrderResponseDTO> orderBuyResponseDTOS(Authentication authentication) {
        User user = ((PrincipalDetails)authentication.getPrincipal()).getUser(); // 현재 로그인 유저
        List<Order> orders = findSuccessBuyOrder(user.getStudentId());
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for(Order order : orders){
            orderResponseDTOS.add(OrderResponseDTO.toOrderBuyerResponseDTO(order));
        }
        return orderResponseDTOS;
    }


}
