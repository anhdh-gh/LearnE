package source.entity;

import java.util.List;

import javax.persistence.*;

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
@Table(name = "lessonexercise")
@SuperBuilder
public class LessonExercise extends AutoIncrementIdBaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @OneToMany(targetEntity = LessonQuestion.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LessonExerciseId", nullable = false)
    @OrderBy(value = "id asc")
    private List<LessonQuestion> lessonQuestions;
}
