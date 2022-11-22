package source.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "testresult", schema = "public")
public class TestResult extends UidBaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Column(name = "Score")
    private float score;

    @Column(name = "CompletionTime")
    private long completionTime;

    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "QuestionId", nullable = false)
    private Question question;
}
