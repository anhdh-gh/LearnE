package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.ErrorCodeConstant;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSignInRequestDto extends BasicRequest {

    @NotNull(message = ErrorCodeConstant.EMAIL_IS_NOT_NULL_400001)
    @Email(message = ErrorCodeConstant.EMAIL_IS_NOT_VALID_400002)
    @Size(min = 6, max = 100, message = ErrorCodeConstant.EMAIL_MUST_HAVE_AT_LEAST_6_CHARACTERS_AND_NO_MORE_THAN_100_CHARACTERS_400003)
    @NotEmpty(message = ErrorCodeConstant.EMAIL_IS_NOT_EMPTY_400023)
    @NotBlank(message = ErrorCodeConstant.EMAIL_IS_NOT_EMPTY_400023)
    private String email;

    /*** The password policy is:
     At least 8 chars
     Contains at least one digit
     Contains at least one lower alpha char and one upper alpha char
     Contains at least one char within a set of special chars (@#%$^ etc.)
     Does not contain space, tab, etc.
     * */
    @NotNull(message = ErrorCodeConstant.PASSWORD_IS_NOT_NULL_400004)
    @NotEmpty(message = ErrorCodeConstant.PASSWORD_IS_NOT_EMPTY_400012)
    @NotBlank(message = ErrorCodeConstant.PASSWORD_IS_NOT_EMPTY_400012)
    private String password;
}
