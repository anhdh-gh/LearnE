package source.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import source.exception.BusinessError;
import source.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Base Response for RestAPI
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class BaseResponse<T> {

    public static final Integer OK_CODE = 200;
    private T data;
    private Metadata meta = new Metadata();

    public static <T> BaseResponse<T> ofSucceeded(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        response.meta.code = OK_CODE;
        response.meta.message = "OK";
        return response;
    }

    public static <T> BaseResponse<T> ofSucceeded() {
        BaseResponse<T> response = new BaseResponse<>();
        response.meta.code = OK_CODE;
        return response;
    }

    public static BaseResponse<Void> ofFailed(BusinessError error) {
        return ofFailed(error, null);
    }

    public static BaseResponse<Void> ofFailed(BusinessError error, String message) {
        return ofFailed(error, message, null);
    }

    public static BaseResponse<Void> ofFailed(BusinessError error, String message, List<FieldViolation> errors) {
        BaseResponse<Void> response = new BaseResponse<>();
        response.meta.code = error.getCode();
        response.meta.message = (message != null) ? message : error.getMessage();
        response.meta.errors = (errors != null) ? new ArrayList<>(errors) : null;
        return response;
    }

    public static BaseResponse<Void> ofFailed(BusinessException exception) {
        return ofFailed(exception.getError(), exception.getMessage());
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        private Integer code;
        private Integer page;
        private Integer size;
        private Long total;
        private List<FieldViolation> errors;
        private String message;
        private String requestId;
    }
}
