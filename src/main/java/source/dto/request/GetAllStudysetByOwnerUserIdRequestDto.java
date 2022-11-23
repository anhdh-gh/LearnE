package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class GetAllStudysetByOwnerUserIdRequestDto extends GetAllStudysetRequestDto {

    private String ownerUserId;

    private String userId;
}
