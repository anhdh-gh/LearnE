package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserComparePasswordRequestDto extends BasicRequest{
    private String idUser;

    private String password;
}
