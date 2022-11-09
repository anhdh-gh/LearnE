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
@Table(name = "lesson", schema = "public")
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lesson extends AutoIncrementIdBaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "duration")
    private String duration;

    @Column(name = "Description")
    private String description;

    @Column(name = "Video")
    private String video;

    @OneToMany(targetEntity = LessonExercise.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LessonId", nullable = false)
    @OrderBy(value = "id asc")
    private List<LessonExercise> lessonExercises;
}
