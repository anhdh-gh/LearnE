package source.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserSignInResponseDto extends TokenResponseDto {

    private User user;
}
