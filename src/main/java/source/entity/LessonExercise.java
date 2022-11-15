package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonexercise", schema = "public")
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
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