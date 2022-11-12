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
        registrationBean.addUrlPatterns(
            RouterConstant.QUESTION_UPLOAD,
            RouterConstant.QUESTION_CHECK_EXIST,
            RouterConstant.QUESTION_DELETE_BY_GROUP_ID
        );
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
