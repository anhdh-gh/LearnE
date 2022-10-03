package source.dto.request;

import source.constant.ErrorCodeConstant;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDeleteByIdRequestDto extends BasicRequest {

    @NotNull(message = ErrorCodeConstant.USER_ID_IS_NOT_NULL_400025)
    @NotEmpty(message = ErrorCodeConstant.USER_ID_IS_NOT_EMPTY_400024)
    @Size(max = 100, message = ErrorCodeConstant.USER_ID_IS_NOT_MORE_THAN_100_CHARACTERS_400026)
    private String id;
}
