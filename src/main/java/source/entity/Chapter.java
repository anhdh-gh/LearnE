package source.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapter")
@SuperBuilder
public class Chapter extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Lesson.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ChapterId", nullable = false)
    private List<Lesson> lessons;
}
