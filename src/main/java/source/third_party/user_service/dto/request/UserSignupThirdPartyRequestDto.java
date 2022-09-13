package source.third_party.user_service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.UserSignupRequestDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupThirdPartyRequestDto extends UserSignupRequestDto {

    private String email;
    private String password;

    @JsonProperty("username")
    private String userName;

    private String id;
}
