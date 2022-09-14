package source.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import source.constant.RouterConstant;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${service.gateway.baseurl}")
    private String allowedOrigin;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping(RouterConstant.SIGN_IN) 
                    .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                    .allowedOrigins(allowedOrigin);
            }
        };
    }
}
