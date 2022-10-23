package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class CheckOwnerStudysetValidRequestDto extends BasicRequest {

    private String studysetId;

    private String ownerUserId;
}
