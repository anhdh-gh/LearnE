package source.third_party.user_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetByIdThirdPartyRequestDto extends BasicRequest {

    private String idUser;
}