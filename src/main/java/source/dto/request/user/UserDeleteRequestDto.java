package source.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteRequestDto extends BasicRequest {

    private String id;
}
