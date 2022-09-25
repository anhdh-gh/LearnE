package source.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonexercise")
public class LessonExercise extends BaseEntity {

    @Column(name = "QuestionId")
    private String questionId;

    @Builder
    public LessonExercise(String id, Date createTime, Date updateTime, String questionId) {
        super(id, createTime, updateTime);
        this.questionId = questionId;
    }
}
