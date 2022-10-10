package source.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import source.entity.enumeration.StatusType;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonexercisestatus")
@SuperBuilder
public class LessonExerciseStatus extends BaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusType status;

    @ManyToOne(targetEntity = LessonExercise.class)
    @JoinColumn (name = "LessonExerciseId", nullable = false)
    private LessonExercise lessonExercise;
}
