package capston.capston.saleProduct.service;

import capston.capston.Error.CustomException;
import capston.capston.Error.ErrorCode;
import capston.capston.auth.PrincipalDetails;
import capston.capston.order.model.Order;
import capston.capston.saleProduct.dto.saleProductCreateDTO.SaleProductCreateRequestDTO;
import capston.capston.saleProduct.dto.saleProductCreateDTO.SaleProductCreateResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindAll.SaleProductFindAllResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindId.SaleProductFindIdResponseDTO;
import capston.capston.saleProduct.dto.saleProductFindmyDTO.SaleProductFindMyResponseDTO;
import capston.capston.saleProduct.dto.saleProductOrderConfirmationDTO.SaleProductOrderConfirmationResponseDTO;
import capston.capston.saleProduct.model.SaleProduct;
import capston.capston.saleProduct.repository.SaleProductRepository;
import capston.capston.user.model.User;
import capston.capston.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleProductService implements SaleProductCommendServiceImpl, SaleProductQueryServiceImpl{

    private final SaleProductRepository saleProductRepository;
    private final UserService userService;


    @Override
    public void save(SaleProduct saleProduct) {
        saleProductRepository.save(saleProduct);
    }

    @Override
    public List<SaleProduct> findMyProductAll(User user) {
        return saleProductRepository.findAllByUser(user);
    }

    @Override
    public List<SaleProduct> findAll() {
        return saleProductRepository.findAll();
    }


    @Override
    public List<SaleProduct> findNotOfferProductAll() {
        return saleProductRepository.findNoneOfferProduct();
    }

    @Override
    public SaleProduct findById(long id) {
        return saleProductRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.NotFoundProductException));
    }



    @Override
    public SaleProductCreateResponseDTO createSaleProduct(Authentication authentication, SaleProductCreateRequestDTO saleProductCreateRequestDTO) {
        SaleProduct saleProduct = saleProductCreateRequestDTO.toEntity(((PrincipalDetails) authentication.getPrincipal()).getUser());
        save(saleProduct); // 저장
        return SaleProductCreateResponseDTO.toSaleProductCreateResponseDTO(saleProduct);
    }

    @Override
    public List<SaleProductFindMyResponseDTO> findMyProduct(Authentication authentication) {
        List<SaleProduct> saleProducts = findMyProductAll(((PrincipalDetails) authentication.getPrincipal()).getUser());

        List<SaleProductFindMyResponseDTO> saleProductFindMyResponseDTOS = new ArrayList<>();

        for(SaleProduct saleProduct : saleProducts){
            saleProductFindMyResponseDTOS.add(SaleProductFindMyResponseDTO.toSaleProductFindMyResponseDTO(saleProduct));
        }
        return saleProductFindMyResponseDTOS;
    }

    @Override
    public List<SaleProductFindAllResponseDTO> findAllProduct() {
        List<SaleProduct> saleProducts = findNotOfferProductAll();

        List<SaleProductFindAllResponseDTO> saleProductFindAllResponseDTOS = new ArrayList<>();
        for(SaleProduct saleProduct : saleProducts){
            saleProductFindAllResponseDTOS.add(SaleProductFindAllResponseDTO.toSaleProductFindAllResponseDTO(saleProduct));
        }
        return saleProductFindAllResponseDTOS;
    }

    @Override
    public SaleProductFindIdResponseDTO findProductId(long id) {
        SaleProduct saleProduct = findById(id);
        return SaleProductFindIdResponseDTO.toSaleProductFindIdResponseDTO(saleProduct);
    }

    // 상품확정은 판매자만 할 수 있다.
    @Override
    public SaleProductOrderConfirmationResponseDTO orderConfirmation(String buyStudentId, long productId, long offerPrice, Authentication authentication) {

        User user = ((PrincipalDetails)authentication.getPrincipal()).getUser(); // 로그인 사람
        SaleProduct saleProduct = findById(productId);
        if(saleProduct.isOfferState()){ // 상품이 확정 되어 있으면
            throw new CustomException(ErrorCode.BadConfirmationException);
        }
        if(!user.getStudentId().equals(saleProduct.getUser().getStudentId())){
            //현재 로그인한 인원이 판매자가 아닐 경우
            throw new CustomException(ErrorCode.BadNotSaleUserException);
        }

        saleProduct.confirmationProduct(offerPrice,buyStudentId);
        save(saleProduct);

        return  SaleProductOrderConfirmationResponseDTO.toSaleProductOrderConfirmationResponseDTO(saleProduct);

    }


}
