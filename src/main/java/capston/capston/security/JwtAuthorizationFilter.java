package capston.capston.security;

import capston.capston.Error.CustomException;
import capston.capston.Error.ErrorCode;
import capston.capston.auth.PrincipalDetails;
import capston.capston.user.model.User;
import capston.capston.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private final   TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {



        // 1. Request header JWT 토큰 추출
        String token = resolveToken(request);


        if(token != null && tokenProvider.validateToken(token, (HttpServletRequest) request)){
            // 토큰이 유효할 경우 토큰에서 Authentication 가져와 SecurityContext 저장
            String userEmail = tokenProvider.TokenInfo(token); // jwt 이용한 eamil 추출

            System.out.println(userEmail);
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(()->new CustomException(ErrorCode.NotFoundUserException));


            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,null,principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        chain.doFilter(request,response);



    }
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return  null;
    }

}
