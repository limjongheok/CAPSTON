package capston.capston.message;

import capston.capston.auth.PrincipalDetails;
import capston.capston.locker.model.Locker;
import capston.capston.locker.service.LockerService;
import capston.capston.saleProduct.service.SaleProductService;
import capston.capston.user.model.User;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MessageService {
    private  final DefaultMessageService messageService;
    private final  LockerService lockerService;
    private  final SaleProductService saleProductService;

    private MessageService(    @Value("${message.apiKey}") String apiKey, @Value("${message.apiSecretKey}") String apiSecretKey,SaleProductService saleProductService, LockerService lockerService) {
        this.lockerService = lockerService;
        this.saleProductService = saleProductService;
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    // 구매자 한테 사물함 지정 문자 보내기
    public SingleMessageSentResponse sendAssignBuyerLocker(Authentication authentication, long lockerId){
        User user = ((PrincipalDetails)authentication.getPrincipal()).getUser();
        Locker locker = lockerService.findByIdLocker(lockerId);
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(createAssignMessage(user,locker)));
        return response;
    }

    // 판매자 한테 사물함 지정 문자 보내기
    public SingleMessageSentResponse sendAssignSaleLocker(long productId, long lockerId){
        User user = saleProductService.findById(productId).getUser();
        Locker locker = lockerService.findByIdLocker(lockerId);
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(createAssignMessage(user,locker)));
        return response;
    }

    // 물건 넣을시 구매자 한테 문자 보내기
    public SingleMessageSentResponse sendPutProductLocker(long lockerId){

        Locker locker = lockerService.findByIdLocker(lockerId);
        User user = locker.getPurchasingUser();
        Message message = new Message();
        message.setFrom("01029463517");
        message.setTo(user.getPhoneNumber()); // 구매자 폰 아이디
        message.setText(locker.getBuildingNum()+" "+"건물" +" " + locker.getId() +" 번 사물함에 주문하신 책이 보관되었습니다.");
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return response;

    }

    // 배정 사물함 문자 보내기
    private Message createAssignMessage(User user, Locker locker){
        Message message = new Message();
        message.setFrom("01029463517");
        message.setTo(user.getPhoneNumber()); // 구매자 폰 아이디
        message.setText("사물함 배정에 성공 하였습니다. 사물함 번호는" +" "+ locker.getId() +"이며" +locker.getBuildingNum()+"번 건물입니다." + "사물함 비밀번호는" + " "+ locker.getLockerPassword() + "입니다.");
        return  message;
    }
}
