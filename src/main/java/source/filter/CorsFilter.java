package source.filter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

  /** The logger. */
  private final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

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

    try {
      ApiKeyVerifyRequestWrapper requestWrapper = new ApiKeyVerifyRequestWrapper(request);

      JSONParser parser = new JSONParser();
      JSONObject dataRequest = StringUtils.isEmpty(requestWrapper.getBody()) ? new JSONObject()
        : (JSONObject) parser.parse(requestWrapper.getBody());
      String requestId = requestWrapper.getHeader("X-Request-ID");
      if (requestId == null || requestId.isEmpty()) {
        requestId = UUID.randomUUID().toString();
      }
      dataRequest.put("request_id", requestId);
      request.setAttribute("request_id", requestId);
      dataRequest.put("uri", request.getRequestURI());
      requestWrapper.setBody(dataRequest.toString());
      chain.doFilter(requestWrapper, res);
    } catch (Exception e) {
      logger.error(e.toString(), e);
      response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.Filter#destroy()
   */
  @Override
  public void destroy() {}

}
