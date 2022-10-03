package source.entity;

import lombok.*;
import source.entity.enumeration.QuestionType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class Question extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "QuestionType")
    private QuestionType questionType;

    @Column(name = "Text")
    private String text;

    @Column(name = "Image")
    private String image;

    @Column(name = "Audio")
    private String audio;

    @OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionId", nullable = false)
    private List<Answer> answers;

    @Builder
    public Question(String id, Date createTime, Date updateTime, QuestionType questionType, String text, String image, String audio, List<Answer> answers) {
        super(id, createTime, updateTime);
        this.questionType = questionType;
        this.text = text;
        this.image = image;
        this.audio = audio;
        this.answers = answers;
    }
}

// https://hocspringboot.net/2020/10/23/onetomany-va-manytoone-trong-spring-boot/