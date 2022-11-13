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
            RouterConstant.USER_AVATAR_UPLOAD,
            RouterConstant.USER_AVATAR_DOWNLOAD,
            RouterConstant.USER_AVATAR_DELETE,

            RouterConstant.QUESTION_UPLOAD
        );
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
