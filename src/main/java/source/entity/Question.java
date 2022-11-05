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
@Table(name = "question", schema = "public")
public class Question extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "QuestionType")
    private QuestionType questionType;

    @Column(name = "Header")
    private String header;

    @Column(name = "Text")
    private String text;

    @Column(name = "Image")
    private String image;

    @Column(name = "Audio")
    private String audio;

    @Column(name = "GroupId")
    private String groupId;

    @OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionId", nullable = false)
    private List<Answer> answers;

    @Builder

    public Question(String id, Date createTime, Date updateTime, QuestionType questionType, String header, String text, String image, String audio, String groupId, List<Answer> answers) {
        super(id, createTime, updateTime);
        this.questionType = questionType;
        this.header = header;
        this.text = text;
        this.image = image;
        this.audio = audio;
        this.groupId = groupId;
        this.answers = answers;
    }
}

// https://hocspringboot.net/2020/10/23/onetomany-va-manytoone-trong-spring-boot/