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
@Table(name = "lesson", schema = "public")
@SuperBuilder
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
