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
@Table(name = "lesson")
@SuperBuilder
public class Lesson extends BaseEntity {

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
    private List<LessonExercise> lessonExercises;
}
