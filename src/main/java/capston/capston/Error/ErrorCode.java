package capston.capston.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
public enum ErrorCode {
    BadPasswordException(400,"BadParameterException","잘못된 password 입니다"),
    BadAuthenticationCodeException(400,"BadAuthenticationCodeException","일치하지 않는 인증코드입니다"),
    BadParameterException(400,"BadParameterException","잘못된 형식의 데이터 입니다."),

    BadBuyerException(400,"BadBuyerException","잘못된 접근 방식 입니다."),
    BadEmailException(400,"BadEmailException","잘못된 형식의 이메일 입니다"),
    BadPhoneNumberException(400,"BadPhoneNumberException","잘못된 형식의 번호입니다"),
    BadAssignLockerException(400,"BadAssignException","사물함이 이미 지정 되었습니다"),
    BadPasswordLockerException(400,"BadPasswordLockerException","사물함 번호가 틀렸습니다."),
    BadPutLockerException(400,"BadPutLockerException","사물함에 이미 물건이 있습니다."),
    BadNonConfirmationException(400,"BadNonConfirmationException","상품 주문 제시가 확정 되어있지 않았습니다."),
    BadConfirmationException(400,"BadConfirmationException","이미 상품 주문 제시가 확정 되었습니다."),
    BadSuccessOrderException(400,"BadSuccessOrderException","이미 주문이 된 상품입니다."),
    BadNotSaleUserException(400,"BadNotSaleUserException","판매 유저만 접근 할 수 있습니다."),
    UnauthorizedException(401,"UnauthorizedException","허용되지 않는 접근입니다."),

    UnauthorizedEmailException(401,"UnauthorizedEmailException","인증되지 않은 이메일입니다."),

    ExpirationException(401,"ExpirationException","만료된 토큰입니다."),
    NotFoundValueException(404,"NotFoundValueException","해당 값이 존재하지 않습니다"),
    NotFoundRefrshTokenException(401,"NotFoundRefreshTokenException","refreshToken이 존재하지 않습니다."),
    NotFoundUserException(404,"NotFoundUserException","계정이 존재하지 않습니다"),
    NotFoundProductException(404,"NotFoundProductException","상품이 존재하지 않습니다"),
    NotFoundLockerException(404,"NotFoundLockerException","사물함이 존재하지 않습니다"),

    NotFoundCartNumDownException(404,"NotFoundException","상품이 1 미만 일 수 없습니다."),
    NotFoundOrderException(404,"NotFoundException","”해당 주문이 존재하지 않습니다.”"),

    DuplicatedEmilException(409,"DuplicatedParameterException","이미 존재하는 이메일입니다."),

    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR")
    ;

    private int status;
    private String errorCode;
    @Setter
    private String message;


}
