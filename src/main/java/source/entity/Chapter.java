package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapter", schema = "public")
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chapter extends AutoIncrementIdBaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Lesson.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ChapterId", nullable = false)
    @OrderBy(value = "id asc")
    private List<Lesson> lessons;
}
