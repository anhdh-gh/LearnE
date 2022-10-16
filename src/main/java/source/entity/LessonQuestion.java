package source.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "lessonquestion", schema = "public")
@SuperBuilder
public class LessonQuestion extends AutoIncrementIdBaseEntity {

    @Column(name = "QuestionId")
    private String questionId;

    @Column(name = "Score")
    private float score;
}
