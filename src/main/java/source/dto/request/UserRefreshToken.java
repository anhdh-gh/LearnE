package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.ErrorCodeConstant;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRefreshToken extends BasicRequest{

    @NotNull(message = ErrorCodeConstant.REFRESH_TOKEN_IS_NOT_NULL_400027)
    @NotBlank(message = ErrorCodeConstant.REFRESH_TOKEN_IS_NOT_EMPTY_400028)
    @NotEmpty(message = ErrorCodeConstant.REFRESH_TOKEN_IS_NOT_EMPTY_400028)
    @Size(max = 500, message = ErrorCodeConstant.EMAIL_MUST_HAVE_AT_LEAST_6_CHARACTERS_AND_NO_MORE_THAN_100_CHARACTERS_400003)
    private String refreshToken;
}
