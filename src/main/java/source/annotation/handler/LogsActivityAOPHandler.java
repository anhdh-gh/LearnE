package source.annotation.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import source.annotation.LogsActivityAnnotation;
import source.dto.request.BasicRequest;
import source.dto.response.BaseResponse;
import source.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.entries;

@Aspect
@Component
@Order(value = 1)
public class LogsActivityAOPHandler {

    private final Logger logger = LoggerFactory.getLogger(LogsActivityAOPHandler.class);

    @Value("${app.name}")
    private String appName;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Around("execution(* *(..)) && @annotation(logsActivityAnnotation)")

    @SneakyThrows
    public Object logsActivityAnnotation(ProceedingJoinPoint point,
         LogsActivityAnnotation logsActivityAnnotation) {

        Object objectRequest = point.getArgs()[0];
        Map<String, Object> mapCustomizeLog = new HashMap<>();
        String requestId = null;
        mapCustomizeLog.put("service_name", appName);
        mapCustomizeLog.put("request_path", httpServletRequest.getRequestURI());
        mapCustomizeLog.put("code_file", point.getSignature().getDeclaringTypeName());
        mapCustomizeLog.put("method_name", point.getSignature().getName());
        mapCustomizeLog.put("message_type", "request");

        if(objectRequest instanceof BasicRequest){
            BasicRequest basicRequest = (BasicRequest) objectRequest;
            requestId = basicRequest.getRequestId();
            mapCustomizeLog.put("request_id", requestId);
        }
        logger.info(objectMapper.writeValueAsString(objectRequest), entries(mapCustomizeLog));
        long timeStart = new Date().getTime();

        Object objectResponse = point.proceed();

        long timeHandle = new Date().getTime() - timeStart;
        mapCustomizeLog.put("execution_time", timeHandle);
        mapCustomizeLog.put("code_file", point.getSignature().getDeclaringTypeName());
        mapCustomizeLog.put("method_name", point.getSignature().getName());
        mapCustomizeLog.put("request_id", requestId);
        mapCustomizeLog.put("message_type", "response");

        logger.info(objectMapper.writeValueAsString(objectRequest), entries(mapCustomizeLog));

        if(objectResponse instanceof ResponseEntity){
            ResponseEntity responseEntity = (ResponseEntity) objectResponse;
            mapCustomizeLog.put("status_code", responseEntity.getStatusCode().value());
            BaseResponse baseResponse = JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
            if(baseResponse.getMeta() != null){
                mapCustomizeLog.put("error_code", baseResponse.getMeta().getCode());
                logger.info(objectMapper.writeValueAsString(baseResponse), entries(mapCustomizeLog));
            }
        }

        return objectResponse;
    }
}

