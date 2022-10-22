package source.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "testresult", schema = "public")
@EqualsAndHashCode(callSuper=false)
public class TestResult extends BaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Column(name = "Score")
    private float score;

    @Column(name = "CompletionTime")
    private long completionTime;

    @ManyToOne(targetEntity = Studyset.class)
    @JoinColumn(name = "StudysetId", nullable = false)
    private Studyset studyset;
}
