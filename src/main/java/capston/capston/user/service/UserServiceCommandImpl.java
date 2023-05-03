package capston.capston.user.service;

import capston.capston.user.dto.userJoinDTO.UserJoinRequestDTO;
import capston.capston.user.dto.userInfoDTO.UserInfoResponseDTO;
import capston.capston.user.dto.userJoinDTO.UserJoinResponseDTO;
import org.springframework.security.core.Authentication;


public interface UserServiceCommandImpl {

    UserJoinResponseDTO create(UserJoinRequestDTO userJoinRequestDTO);

    UserInfoResponseDTO userInfo(Authentication authentication);



}
