package source.third_party.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGetByIdThirdPartyResponseDto {

    private String id;
    private String role;
    private String email;
    private String password;
}