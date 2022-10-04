package source.filter;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.json.simple.parser.JSONParser;
import source.constant.ContentTypeConstant;
import source.constant.JwtTokenTypeConstant;
import source.constant.RequestKeyConstant;
import source.entity.User;
import source.util.JwtUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    /** The logger. */
    private Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    private final JwtUtil jwtUtil = new JwtUtil();

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String contentType = request.getHeader(RequestKeyConstant.CONTENT_TYPE);
        String requestId = request.getHeader(RequestKeyConstant.X_REQUEST_ID);
        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
        }
        request.setAttribute(RequestKeyConstant.REQUEST_ID, requestId);

        if(contentType != null && contentType.trim().toLowerCase().contains(ContentTypeConstant.MULTIPART_FORM_DATA)) {
            chain.doFilter(request, response);
        } else {
            try {
                ApiKeyVerifyRequestWrapper requestWrapper = new ApiKeyVerifyRequestWrapper(request);

                JSONParser parser = new JSONParser();
                JSONObject dataRequest = ObjectUtils.isEmpty(requestWrapper.getBody())
                        ? new JSONObject()
                        : (JSONObject) parser.parse(requestWrapper.getBody());

                dataRequest.put(RequestKeyConstant.REQUEST_ID, requestId);
                dataRequest.put(RequestKeyConstant.URI, request.getRequestURI());
                requestWrapper.setBody(dataRequest.toString());

                // Set user auth if exists
                String jwt = getJwtFromRequest(request);
                if (StringUtils.hasText(jwt) && jwtUtil.validateJwtToken(jwt)) {
                    User userAuth = jwtUtil.getUserFromJwtToken(jwt);
                    if(userAuth != null) {
                        request.setAttribute(RequestKeyConstant.USER_AUTH, userAuth);
                        dataRequest.put(RequestKeyConstant.USER_AUTH, userAuth);
                    }
                }

                chain.doFilter(requestWrapper, res);
            } catch (Exception e) {
                logger.error(e.toString(), e);
                response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            }
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

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {}
}