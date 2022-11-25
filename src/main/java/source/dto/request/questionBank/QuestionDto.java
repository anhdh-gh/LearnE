package source.dto.request.questionBank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.BaseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionDto extends BaseDto {

    private String text;

    private Integer time;

    private String pdf;

    private List<AnswerDto> answers;

    private String questionResult;
}