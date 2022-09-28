package source.filter.authorization;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import source.constant.RouterConstant;

@Configuration
public class AuthorizationConfiguration {

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns(RouterConstant.USER_GET_ALL,
                RouterConstant.ADMIN_DELETE_USER,
                RouterConstant.USER_GET_BY_ID,
                RouterConstant.QUESTION_CREATE);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
