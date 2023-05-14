package capston.capston.auth;



import capston.capston.Error.CustomException;
import capston.capston.Error.ErrorCode;
import capston.capston.user.model.User;
import capston.capston.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService");
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new CustomException(ErrorCode.NotFoundUserException));

        System.out.println(user); //user  찍히고
        return  new PrincipalDetails(user);
    }
}
