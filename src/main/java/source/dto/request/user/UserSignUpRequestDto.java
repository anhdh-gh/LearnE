package source.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDto extends BasicRequest {

    private String email;
    private String password;

    @JsonProperty("username")
    private String userName;
}
