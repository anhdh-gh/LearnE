package source.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonquestion")
@SuperBuilder
public class LessonQuestion extends BaseEntity {

    @Column(name = "QuestionId")
    private String questionId;

    @Column(name = "Score")
    private float score;
}
