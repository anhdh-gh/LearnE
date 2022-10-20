package source.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "studyset", schema = "public")
@EqualsAndHashCode(callSuper=false)
public class Studyset extends BaseEntity {

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @OneToMany(targetEntity = WordCard.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "StudysetId", nullable = false)
    private List<WordCard> wordCards;
}
