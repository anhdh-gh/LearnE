package source.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AuthSignUpRequestDto extends BasicRequest{


    @NotNull(message = "400001")
    @Email(message = "400002")
    @Size(min = 6, max = 100, message = "400003")
    private String email;

    @NotNull(message = "400004")
    @Size(min = 6, max = 500, message = "400005")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "400006")
    private String password;

    @JsonProperty("username")
    @NotNull(message = "400007")
    @Size(max = 500, message = "400008")
    private String userName;
}
