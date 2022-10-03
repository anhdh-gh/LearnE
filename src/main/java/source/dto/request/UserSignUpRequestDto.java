package source.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.ErrorCodeConstant;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSignUpRequestDto extends BasicRequest {

    @NotNull(message = ErrorCodeConstant.EMAIL_IS_NOT_NULL_400001)
    @Email(message = ErrorCodeConstant.EMAIL_IS_NOT_VALID_400002)
    @Size(min = 6, max = 100, message = ErrorCodeConstant.EMAIL_MUST_HAVE_AT_LEAST_6_CHARACTERS_AND_NO_MORE_THAN_100_CHARACTERS_400003)
    @NotEmpty(message = ErrorCodeConstant.EMAIL_IS_NOT_EMPTY_400023)
    @NotBlank(message = ErrorCodeConstant.EMAIL_IS_NOT_EMPTY_400023)
    private String email;

    @NotNull(message = ErrorCodeConstant.PASSWORD_IS_NOT_NULL_400004)
    @Size(min = 6, max = 500, message = ErrorCodeConstant.PASSWORD_MUST_NO_MORE_THAN_100_CHARACTERS_400005)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = ErrorCodeConstant.PASSWORD_IS_NOT_STRONG_400006)
    private String password;

    @JsonProperty("username")
    @NotNull(message = ErrorCodeConstant.USERNAME_IS_NOT_NULL_400007)
    @Size(max = 100, message = ErrorCodeConstant.USERNAME_MUST_NO_MORE_THAN_100_CHARACTERS_400008)
    private String userName;
}
