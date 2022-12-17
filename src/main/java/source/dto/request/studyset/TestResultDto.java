package source.dto.request.studyset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.BaseDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class TestResultDto extends BaseDto {

    private String userId;

    private float score;

    private long completionTime;

    @JsonIgnore
    private StudysetDto studyset;

    private String studysetId;
}