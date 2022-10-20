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
@Table(name = "examination", schema = "public")
@EqualsAndHashCode(callSuper=false)
public class Examination extends BaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Column(name = "Score")
    private float score;

    @ManyToOne(targetEntity = Studyset.class)
    @JoinColumn(name = "StudysetId", nullable = false)
    private Studyset studyset;
}
