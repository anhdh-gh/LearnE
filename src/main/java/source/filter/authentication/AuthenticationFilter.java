package source.filter.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import source.constant.RequestKeyConstant;
import source.dto.response.BaseResponse;
import source.entity.User;
import source.exception.BusinessErrors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            User user = (User) request.getAttribute(RequestKeyConstant.USER_AUTH);
            if (user != null) {
                filterChain.doFilter(request, response);
            } else {
                handlerError(request, response);
            }
        } catch (Exception e) {
            handlerError(request, response);
        }
    }

    private void handlerError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader(RequestKeyConstant.CONTENT_TYPE, "application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(BaseResponse.ofFailed((String) request.getAttribute(RequestKeyConstant.REQUEST_ID), BusinessErrors.UNAUTHORIZED)));
        writer.flush();
        writer.close();
    }
}
