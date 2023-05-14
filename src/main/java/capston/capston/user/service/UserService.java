package capston.capston.user.service;

import capston.capston.Error.CustomException;
import capston.capston.Error.ErrorCode;
import capston.capston.auth.PrincipalDetails;
import capston.capston.security.Role;
import capston.capston.user.dto.userJoinDTO.UserJoinRequestDTO;
import capston.capston.user.dto.userInfoDTO.UserInfoResponseDTO;
import capston.capston.user.dto.userJoinDTO.UserJoinResponseDTO;
import capston.capston.user.model.User;
import capston.capston.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserServiceCommandImpl,UserServiceQueryImpl {
    private final UserRepository userRepository;
    private final EmailAuthenticationService emailAuthenticationService;

    private final BCryptPasswordEncoder passwordEncoder;

    // userQuery
    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public User findByStudentId(String studentId) {
        User user = userRepository.findByStudentId(studentId).orElseThrow(
                ()-> new CustomException(ErrorCode.NotFoundUserException)
        );
        return user;
    }


    //userCommand
    @Override
    public UserJoinResponseDTO create(UserJoinRequestDTO userJoinRequestDTO) {
        //이메일 중복 체크
        if(userRepository.existsByEmail(userJoinRequestDTO.getEmail())){
            throw new CustomException(ErrorCode.DuplicatedEmilException);
        }
        if(!emailAuthenticationService.isEmailAuthenticated(userJoinRequestDTO.getEmail())){
            throw new CustomException(ErrorCode.UnauthorizedEmailException);
        }


        User user = userJoinRequestDTO.toEntity(passwordEncoder.encode(userJoinRequestDTO.getPassword()), Role.ROLE_USER,getStudentId(userJoinRequestDTO.getEmail()));

        save(user);
        return UserJoinResponseDTO.toUserJoinResponseDTO(user);



    }

    @Override
    public UserInfoResponseDTO userInfo(Authentication authentication) {

        User user = ((PrincipalDetails)authentication.getPrincipal()).getUser();

        return UserInfoResponseDTO.toUserInfoResponseDTO(user);
    }

    private String getStudentId(String email){
        return email.substring(0,email.indexOf("@"));

    }
}
