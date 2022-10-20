package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class AddOrUpdateExaminationRequestDto extends BasicRequest {

    private String userId;

    private String studysetId;

    private float score;
}
