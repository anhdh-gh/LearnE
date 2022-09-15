package source.filter.authentication;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import source.constant.RouterConstant;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns(
            RouterConstant.USER_GET_ALL,
            RouterConstant.USER_DELETE,
            RouterConstant.USER_UPDATE,
            RouterConstant.USER_GET_INFO,
            RouterConstant.USER_GET_BY_ID
        );
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
