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
@Table(name = "lessonexercise")
public class LessonExercise extends BaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @OneToMany(targetEntity = LessonQuestion.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LessonExerciseId", nullable = false)
    private List<LessonQuestion> lessonQuestions;

    @Builder
    public LessonExercise(String id, Date createTime, Date updateTime, String name, String description, List<LessonQuestion> lessonQuestions) {
        super(id, createTime, updateTime);
        this.name = name;
        this.description = description;
        this.lessonQuestions = lessonQuestions;
    }
}
