package source.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInRequestDto extends BasicRequest {

    private String email;
    private String password;
}