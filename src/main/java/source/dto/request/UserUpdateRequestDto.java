package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.enumeration.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto extends BasicRequest{

    private String id;

    private Role role;
}
