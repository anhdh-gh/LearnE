package source.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestResultDto extends BaseDto {

    private String userId;

    private UserDto user;

    private float score;

    private long completionTime;

    @JsonIgnore
    private QuestionDto question;

    private String questionId;
}
