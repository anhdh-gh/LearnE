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
@Table(name = "lessonquestion")
public class LessonQuestion extends BaseEntity {

    @Column(name = "QuestionId")
    private String questionId;

    @Column(name = "Score")
    private float score;

    @Builder
    public LessonQuestion(String id, Date createTime, Date updateTime, String questionId, float score) {
        super(id, createTime, updateTime);
        this.questionId = questionId;
        this.score = score;
    }
}
