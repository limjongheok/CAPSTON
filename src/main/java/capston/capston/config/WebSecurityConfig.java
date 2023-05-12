package capston.capston.config;

import capston.capston.security.CustomAuthenticationEntryPoint;
import capston.capston.security.JwtAuthenticationFilter;
import capston.capston.security.JwtAuthorizationFilter;
import capston.capston.security.TokenProvider;
import capston.capston.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;


    private final    RedisTemplate<String, Object> redisTemplate;


    private static final String[] PERMIT_URL_ARRAY = {
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**",



            /* swagger v2 */
            "/api/v2/api-docs",
            "/api/swagger-resources",
            "/api/swagger-resources/**",
            "/api/configuration/ui",
            "/api/configuration/security",
            "/api/swagger-ui.html",
            "/api/webjars/**",
            /* swagger v3 */
            "/api/v3/api-docs/**",
            "/api/swagger-ui/**",

    };




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http

                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // cors 필터 걸기
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()// preFlight 허횽
                .antMatchers("/api/join").permitAll() //회원 가입
                .antMatchers("/api/login").permitAll() // 로그인
                .antMatchers("/api/email/send/auth/check").permitAll() // 인증코드 보내기
                .antMatchers("/api/email/send/auth").permitAll() // 인증코드 체크
                .antMatchers("/api/saleproduct/find/all").permitAll() // 판매 상품 모두 보기
                .antMatchers("/api/reissuance/refreshToken").permitAll()// refreshtoken  재 발급
                .antMatchers("/api/locker/*").permitAll()
                .antMatchers(PERMIT_URL_ARRAY).permitAll()
                .antMatchers("/api/saleproduct/*").hasRole("USER")
                .antMatchers("/api/user/*").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());


        http.addFilterBefore(new JwtAuthorizationFilter(tokenProvider, userRepository), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(),tokenProvider,redisTemplate ), UsernamePasswordAuthenticationFilter.class);

        http.cors();

    }



}

