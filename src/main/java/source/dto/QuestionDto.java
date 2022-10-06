package source.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.enumeration.QuestionType;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto extends BaseDto{

    private QuestionType questionType;

    private String text;

    private String image;

    private String audio;

    private float score;

    private List<AnswerDto> answers;

}
