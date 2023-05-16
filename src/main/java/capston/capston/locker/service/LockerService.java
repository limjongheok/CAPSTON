package capston.capston.locker.service;

import capston.capston.Error.CustomException;
import capston.capston.Error.ErrorCode;

import capston.capston.auth.PrincipalDetails;
import capston.capston.locker.dto.lockerAssignDTO.LockerAssignResponseDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateRequestDTO;
import capston.capston.locker.dto.lockerCreateDTO.LockerCreateResponseDTO;
import capston.capston.locker.dto.lockerPasswordCheckDTO.LockerPasswordCheckResponseDTO;
import capston.capston.locker.dto.lockerProdctDTO.LockerProductResponseDTO;
import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedRequestDTO;
import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedResponseDTO;
import capston.capston.locker.model.Locker;
import capston.capston.locker.repository.LockerRepository;
import capston.capston.saleProduct.model.SaleProduct;
import capston.capston.saleProduct.service.SaleProductService;
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
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LockerService implements LockerServiceCommandImpl, LockerServiceQueryImpl{

    private final LockerRepository lockerRepository;
    private  final SaleProductService saleProductService;
    private  final UserService userService;

    // query
    @Override
    public void save(Locker locker) {
        lockerRepository.save(locker);
    }



    @Override
    public List<Locker> getNotAssignLocker(LockerUnassignedRequestDTO lockerUnassignedRequestDTO) {
        return lockerRepository.getNotAssignLocker(lockerUnassignedRequestDTO.getBuildingNum());
    }


    @Override
    public Locker findByIdLocker(long id) {
        Locker locker = lockerRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.NotFoundLockerException)
        );
        return locker;
    }

    /// command

    // 사물함 생성
    @Override
    public LockerCreateResponseDTO createLocker(LockerCreateRequestDTO lockerCreateRequestDTO) {
        Locker locker = lockerCreateRequestDTO.toCreateLocker();
        save(locker);
        return LockerCreateResponseDTO.toLockerCreateResponseDTO(locker);
    }

    // 건물번호 및 배정 받지 않은 사물함 찾기
    @Override
    public List<LockerUnassignedResponseDTO> unassignedLocker(LockerUnassignedRequestDTO lockerUnassignedRequestDTO) {
        List<Locker> unassignedLocker = getNotAssignLocker(lockerUnassignedRequestDTO);
        List<LockerUnassignedResponseDTO> unassignedResponseDTOList = new ArrayList<>();
        for(Locker locker: unassignedLocker){
            unassignedResponseDTOList.add(LockerUnassignedResponseDTO.toLockerUnassignedResponseDTO(locker));
        }
        return unassignedResponseDTOList;
    }

    // 물품 배정하기
    // 배정 후 구매자 판매자 에게 문자 보내기
    @Override
    public LockerAssignResponseDTO assignLocker(String buyStudentId, long productId, long lockerId, Authentication authentication) {
        Locker locker = findByIdLocker(lockerId); // 배정 받지 않은 사물함 찾기
        if(locker.isCheckAssign()){
            // 사물함이 이미 배정 받았다면
            throw  new CustomException(ErrorCode.BadAssignLockerException);
        }

        User user = ((PrincipalDetails)authentication.getPrincipal()).getUser(); //  로그인유저
        SaleProduct saleProduct = saleProductService.findById(productId);// 상품 찾기

        if(!saleProduct.getUser().getStudentId().equals(user.getStudentId())){
            // 로그인 유저가 판매유저가 아닌 경우
            throw  new CustomException(ErrorCode.BadNotSaleUserException);
        }
        User buyUser = userService.findByStudentId(buyStudentId); // 구매 유저 찾기
        locker.assignLocker(buyUser,saleProduct);
        save(locker);
        return LockerAssignResponseDTO.toLockerAssignResponseDTO(locker);
    }

    // 판매자
    @Override
    public LockerPasswordCheckResponseDTO saleCheckPassword(long lockerId, String lockerPassword) {
        Locker locker = findByIdLocker(lockerId); // 해당 사물함 찾기

        if(locker.isCheckProduct()){ // 해당 사물함에 물건이 있을시 판매자가 아닌 구매자
            throw  new CustomException(ErrorCode.BadBuyerException);
        }
        if(locker.getLockerPassword().equals(lockerPassword)){
            log.info(lockerPassword);
            return LockerPasswordCheckResponseDTO.toLockerPasswordCheckResponseDTO(locker);

        }else{
            throw new CustomException(ErrorCode.BadPasswordException);
        }

    }

    // 사물함에 물건 넣기
    // 물건 넣은 후 문자 보내기
    @Override
    public LockerProductResponseDTO putProduct(long lockerId) {
        Locker locker = findByIdLocker(lockerId);
        if(locker.isCheckProduct()){
            throw  new CustomException(ErrorCode.BadPutLockerException);
        }
        locker.putProductLocker(); // 물건 넣고
        save(locker); // 저장하기

        // 문자 보내기
        return LockerProductResponseDTO.toLockerPutProductResponseDTO(locker);
    }

    @Override
    public LockerProductResponseDTO pushProduct(long lockerId) {
        Locker locker = findByIdLocker(lockerId);
        locker.pushProductLocker(); // 물건 빼기
        save(locker);
        return LockerProductResponseDTO.toLockerPutProductResponseDTO(locker);
    }

    @Override
    public LockerPasswordCheckResponseDTO buyerCheckPassword(long lockerId, String lockerPassword) {
        Locker locker = findByIdLocker(lockerId);
        if(locker.getLockerPassword().equals(lockerPassword)){
            return LockerPasswordCheckResponseDTO.toLockerPasswordCheckResponseDTO(locker);

        }else{
            throw new CustomException(ErrorCode.BadPasswordException);
        }
    }



}
