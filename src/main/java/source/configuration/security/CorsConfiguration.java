package source.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Value("${service.gateway.baseurl}")
    private String allowedOrigin;

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping("/**") // Cho phép mọi nguồn
                    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                    .allowedOrigins(allowedOrigin);
            }
        };
    }
}

// https://stackoverflow.com/questions/35091524/spring-cors-no-access-control-allow-origin-header-is-present
// https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-cors
