package source.third_party.multimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import source.constant.RequestKeyConstant;
import source.dto.request.BasicRequest;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class BaseService {

    @Autowired
    protected Environment environment;

    @Autowired
    protected RestTemplate restTemplate;

    protected HttpEntity<BasicRequest> getHeader(BasicRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(RequestKeyConstant.X_REQUEST_ID, request.getRequestId());
        headers.set(RequestKeyConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }
}
