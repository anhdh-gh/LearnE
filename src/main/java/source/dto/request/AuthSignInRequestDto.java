package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthSignInRequestDto extends BasicRequest{

    @NotNull(message = "400001")
    @Email(message = "400002")
    @Size(min = 6, max = 100, message = "400003")
    private String email;

    /*** The password policy is:
        At least 8 chars
        Contains at least one digit
        Contains at least one lower alpha char and one upper alpha char
        Contains at least one char within a set of special chars (@#%$^ etc.)
        Does not contain space, tab, etc.
    * */
    @NotNull(message = "400004")
    @Size(min = 6, max = 500, message = "400005")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "400006")
    private String password;

}
