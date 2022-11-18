package source.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.enumeration.QuestionType;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "question", schema = "public")
public class Question extends UidBaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "QuestionType")
    private QuestionType questionType;

    @Column(name = "Header")
    private String header;

    @Column(name = "Text")
    private String text;

    @Column(name = "Image")
    private String image;

    @Column(name = "Time")
    private Integer time;

    @Column(name = "Audio")
    private String audio;

    @Column(name = "Pdf")
    private String pdf;

    @Column(name = "GroupId")
    private String groupId;

    @OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionId", nullable = false)
    private List<Answer> answers;
}

// https://hocspringboot.net/2020/10/23/onetomany-va-manytoone-trong-spring-boot/