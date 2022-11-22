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
                RouterConstant.USER_DELETE,
                RouterConstant.USER_UPDATE,
                RouterConstant.USER_GET_INFO,
                RouterConstant.USER_GET_BY_ID,
                RouterConstant.ADMIN_DELETE_USER,
                RouterConstant.COURSE_CREATE,
                RouterConstant.COURSE_UPDATE_LESSON_STATUS,
                RouterConstant.CREATE_STUDYSET,
                RouterConstant.UPDATE_STUDYSET,
                RouterConstant.SAVE_STUDYSET_TEST_RESULT,
                RouterConstant.DELETE_STUDYSET_BY_ID,
                RouterConstant.CHECK_OWNER_STUDYSET_VALID,
                RouterConstant.COURSE_DELETE_BY_ID,
                RouterConstant.COURSE_UPDATE,
                RouterConstant.QUESTION_CREATE,
                RouterConstant.COURSE_UPDATE,
                RouterConstant.QUESTION_DELETE_BY_ID
        );
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
