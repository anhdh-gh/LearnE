package source.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Video")
    private String video;

    @OneToMany(targetEntity = LessonExercise.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LessonId", nullable = false)
    private List<LessonExercise> lessonExercises;

    @Builder
    public Lesson(String id, Date createTime, Date updateTime, String name, String description, String video, List<LessonExercise> lessonExercises) {
        super(id, createTime, updateTime);
        this.name = name;
        this.description = description;
        this.video = video;
        this.lessonExercises = lessonExercises;
    }
}
