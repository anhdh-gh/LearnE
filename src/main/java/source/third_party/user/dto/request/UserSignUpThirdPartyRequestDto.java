package source.third_party.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.UserSignUpRequestDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpThirdPartyRequestDto extends UserSignUpRequestDto {

    private String email;
    private String password;

    @JsonProperty("username")
    private String userName;

    private String id;
}
