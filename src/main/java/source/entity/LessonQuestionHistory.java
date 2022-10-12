package source.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonquestionhistory")
@SuperBuilder
public class LessonQuestionHistory extends UidBaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Column(name = "Score")
    private float score;

    @OneToMany(targetEntity = AnswerChoice.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LessonQuestionHistoryId", nullable = false)
    private List<AnswerChoice> answerChoices;
}
