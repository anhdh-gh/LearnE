package source.dto.request.questionBank;

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

    private UserDto user;

    private float score;

    private long completionTime;

    @JsonIgnore
    private QuestionDto question;

    private String questionId;
}
