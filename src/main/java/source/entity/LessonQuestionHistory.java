package source.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import source.entity.enumeration.StatusType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonquestionhistory")
@SuperBuilder
public class LessonQuestionHistory extends BaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Column(name = "Score")
    private float score;

    @OneToMany(targetEntity = AnswerChoice.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LessonQuestionHistoryId", nullable = false)
    private List<AnswerChoice> answerChoices;
}
