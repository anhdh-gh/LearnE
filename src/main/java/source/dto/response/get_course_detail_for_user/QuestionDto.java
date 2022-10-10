package source.dto.response.get_course_detail_for_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.BaseEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionDto extends BaseEntity {

    private QuestionType questionType;

    private String text;

    private String image;

    private String audio;

    private List<AnswerDto> answers;

    private float score;
}
