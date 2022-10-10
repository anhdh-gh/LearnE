package source.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonexercise")
@SuperBuilder
public class LessonExercise extends BaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @OneToMany(targetEntity = LessonQuestion.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LessonExerciseId", nullable = false)
    private List<LessonQuestion> lessonQuestions;
}
