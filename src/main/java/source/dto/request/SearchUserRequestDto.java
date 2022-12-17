package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchUserRequestDto extends BasicRequest {

    private int page;

    private int size;

    private String userName;
}
