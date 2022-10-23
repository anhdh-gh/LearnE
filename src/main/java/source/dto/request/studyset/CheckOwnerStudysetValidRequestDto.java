package source.dto.request.studyset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class CheckOwnerStudysetValidRequestDto extends BasicRequest {

    private String studysetId;

    private String ownerUserId;
}
