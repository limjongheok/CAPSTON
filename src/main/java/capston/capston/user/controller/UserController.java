package capston.capston.user.controller;

import capston.capston.user.dto.emailAuthenticationDTO.CheckAuthCodeRequestDTO;
import capston.capston.user.dto.emailAuthenticationDTO.SendAuthCodeRequestDTO;
import capston.capston.user.dto.userJoinDTO.UserJoinRequestDTO;
import capston.capston.user.dto.emailAuthenticationDTO.CheckAuthCodeResponseDTO;
import capston.capston.user.dto.emailAuthenticationDTO.SendAuthCodeResponseDTO;
import capston.capston.user.dto.userInfoDTO.UserInfoResponseDTO;
import capston.capston.user.dto.userJoinDTO.UserJoinResponseDTO;
import capston.capston.user.service.EmailAuthenticationService;
import capston.capston.user.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final EmailAuthenticationService emailAuthenticationService;
    private final UserService userService;


    // 인증 코드 보내기
    @PostMapping("/api/email/send/auth")
    public ResponseEntity<?> sendAuthCodeEmail(@RequestBody @Valid SendAuthCodeRequestDTO sendAuthCodeRequestDTO) throws MessagingException, UnsupportedEncodingException {
        SendAuthCodeResponseDTO sendAuthCodeResponseDTO = emailAuthenticationService.sendAuthenticationCode(sendAuthCodeRequestDTO);
        return ResponseEntity.ok().body(createResponse(sendAuthCodeResponseDTO, "이메일 전송이 완료되었습니다."));
    }

    // 인증 코드 체크
    @PostMapping("/api/email/send/auth/check")
    public ResponseEntity<?> checkAuthCodeEmail(@RequestBody @Valid CheckAuthCodeRequestDTO checkAuthCodeRequestDTO){
        CheckAuthCodeResponseDTO checkAuthCodeResponseDTO = emailAuthenticationService.checkAuthCode(checkAuthCodeRequestDTO);
        return ResponseEntity.ok().body(createResponse(checkAuthCodeResponseDTO,"이메일 인증이 완료되었습니다."));
    }

    // 회원 가입

    @PostMapping("/api/join")
    public ResponseEntity<?> signup(@RequestBody @Valid UserJoinRequestDTO userJoinRequestDTO){
        UserJoinResponseDTO userJoinResponseDTO = userService.create(userJoinRequestDTO);
        return ResponseEntity.ok().body(createResponse(userJoinResponseDTO,"회원가입에 성공했습니다."));
    }

    // user 정보 조회
    @GetMapping("/api/user/info")
    public ResponseEntity<?> userInfo(Authentication authentication){
        UserInfoResponseDTO userInfoResponseDTO = userService.userInfo(authentication);
        return ResponseEntity.ok().body(createResponse(userInfoResponseDTO,"유저 조회에 성공하였습니다.."));
    }


    private Map<String,Object> createResponse(Object object, String msg){
        Map<String, Object> response = new HashMap<>();
        response.put("msg", msg);
        response.put("data", object);
        return response;
    }



}
