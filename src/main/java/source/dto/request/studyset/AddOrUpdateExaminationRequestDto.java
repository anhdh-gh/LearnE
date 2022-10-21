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
public class AddOrUpdateExaminationRequestDto extends BasicRequest {

    private String userId;

    private String studysetId;

    private float score;
}
