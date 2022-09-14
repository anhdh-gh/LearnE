package source.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import source.constant.RouterConstant;
import source.entity.enumeration.Role;

@Configuration
public class WebSecurityConfiguration {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Ngăn chặn request từ một domain khác
            .and()
            .csrf().disable() // Tăt bảo vệ CSRF
            .authorizeRequests()

            // Cho phép mọi người truy cập vào các đường dẫn này
            .antMatchers(
                RouterConstant.SIGN_IN,
                RouterConstant.SIGN_UP
            ).permitAll()

            // Cho phép Admin truy cập đường dẫn này
            .antMatchers(

            ).hasAuthority(Role.ADMIN.getValue())

            // Cho phép User truy cập đường dẫn này
            .antMatchers(

            ).hasAuthority(Role.USER.getValue())

            // Tất cả các request khác đều cần phải xác thực mới được truy cập
            .anyRequest().authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
