package source.entity;

import lombok.*;
import source.entity.enumeration.StatusType;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonexercisestatus")
public class LessonExerciseStatus extends BaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusType status;

    @Column(name = "Score")
    private float score;

    @ManyToOne(targetEntity = LessonExercise.class)
    @JoinColumn (name = "LessonExerciseId", nullable = false)
    private LessonExercise lessonExercise;

    @Builder
    public LessonExerciseStatus(String id, Date createTime, Date updateTime, String userId, StatusType status, float score, LessonExercise lessonExercise) {
        super(id, createTime, updateTime);
        this.userId = userId;
        this.status = status;
        this.score = score;
        this.lessonExercise = lessonExercise;
    }
}
