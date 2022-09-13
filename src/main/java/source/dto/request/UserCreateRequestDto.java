package source.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto extends BasicRequest {

    @JsonProperty("username")
    private String userName;

    private String password;

    private String email;

    private String id;
}
