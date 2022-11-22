package source.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    @Column(name = "Text")
    private String text;

    @Column(name = "Time")
    private Integer time;

    @Column(name = "Pdf")
    private String pdf;

    @OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionId", nullable = false)
    private List<Answer> answers;
}

// https://hocspringboot.net/2020/10/23/onetomany-va-manytoone-trong-spring-boot/