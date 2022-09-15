package source.filter.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import source.constant.JwtTokenTypeConstant;
import source.constant.RequestKeyConstant;
import source.dto.response.BaseResponse;
import source.entity.User;
import source.exception.BusinessErrors;
import source.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper mapper = new ObjectMapper();

    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtUtil.validateJwtToken(jwt)) {
                User user = jwtUtil.getUserFromJwtToken(jwt);
                if(user != null) {
                    request.setAttribute(RequestKeyConstant.USER, user);
                    filterChain.doFilter(request, response);
                } else {
                    handlerError(request, response);
                }
            } else {
                handlerError(request, response);
            }
        } catch (Exception ex) {
            handlerError(request, response);
            log.error("failed on set user authentication", ex);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtTokenTypeConstant.BEARER + " ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void handlerError(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setHeader(RequestKeyConstant.CONTENT_TYPE, "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(BaseResponse.ofFailed((String) request.getAttribute(RequestKeyConstant.REQUEST_ID), BusinessErrors.UNAUTHORIZED)));
        writer.flush();
        writer.close();
    }
}
