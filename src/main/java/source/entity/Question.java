package source.entity;

import javax.persistence.*;
import lombok.*;
import source.entity.enumeration.QuestionType;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
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

    @OneToMany(targetEntity = Answer.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionId")
    private List<Answer> answers;
}

// https://hocspringboot.net/2020/10/23/onetomany-va-manytoone-trong-spring-boot/