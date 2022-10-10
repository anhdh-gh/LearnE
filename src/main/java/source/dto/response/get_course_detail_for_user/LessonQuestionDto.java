package source.dto.response.get_course_detail_for_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.LessonQuestion;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LessonQuestionDto extends LessonQuestion {

    private QuestionDto question;
}
