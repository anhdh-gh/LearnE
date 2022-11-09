package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonquestionhistory", schema = "public")
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonQuestionHistory extends UidBaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Column(name = "Score")
    private float score;

    @OneToMany(targetEntity = AnswerChoice.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LessonQuestionHistoryId", nullable = false)
    private List<AnswerChoice> answerChoices;
}
