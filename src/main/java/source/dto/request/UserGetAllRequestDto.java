package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetAllRequestDto extends BasicRequest{
    private int page;

    private int size;
}