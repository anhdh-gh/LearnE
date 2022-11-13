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
            RouterConstant.ADMIN_DELETE_USER,
            RouterConstant.USER_GET_BY_ID,
            RouterConstant.COURSE_CREATE,
            RouterConstant.COURSE_DELETE_BY_ID,
            RouterConstant.COURSE_UPDATE,
            RouterConstant.QUESTION_CREATE_LIST,
            RouterConstant.QUESTION_DELETE_BY_GROUP_QUESTION_ID
        );
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
