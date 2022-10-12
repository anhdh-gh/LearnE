package source.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapter")
@SuperBuilder
public class Chapter extends AutoIncrementIdBaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Lesson.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ChapterId", nullable = false)
    @OrderBy(value = "id asc")
    private List<Lesson> lessons;
}
