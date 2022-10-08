package source.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetInfoRequestDto extends BasicRequest {
    private String id;
}
