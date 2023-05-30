package capston.capston.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private  final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("http://127.0.0.1:5000")
                .allowedOrigins("http://59.26.59.60:5000")
                .allowedOrigins("http://59.26.59.60:8080")
                .allowedOrigins("http://localhost")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("Access-Control-Allow-origin","*")
                .allowCredentials(true)
                .exposedHeaders("Authorization","*")
                .maxAge(MAX_AGE_SECS)
        ;
    }
}
