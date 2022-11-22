package source.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto extends BaseDto {

    private String text;

    private Integer time;

    private String pdf;

    private List<AnswerDto> answers;

    private Long userCount;

    private TestResultDto testResult;
}