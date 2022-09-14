package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.ErrorCodeConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDeleteRequestDto extends BasicRequest{

    @Size(max = 100, message = ErrorCodeConstant.USER_ID_IS_NOT_MORE_THAN_100_CHARACTERS_400026)
    @NotNull(message = ErrorCodeConstant.USER_ID_IS_NOT_NULL_400025)
    @NotBlank(message = ErrorCodeConstant.USER_ID_IS_NOT_EMPTY_400024)
    @NotEmpty(message = ErrorCodeConstant.USER_ID_IS_NOT_EMPTY_400024)
    private String id;
}
