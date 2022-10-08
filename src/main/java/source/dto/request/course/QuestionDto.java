package source.dto.request.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.BaseDto;
import source.entity.enumeration.QuestionType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto extends BaseDto {

    private QuestionType questionType;

    private String text;

    private String image;

    private String audio;

    private float score;

    private List<AnswerDto> answers;

}
