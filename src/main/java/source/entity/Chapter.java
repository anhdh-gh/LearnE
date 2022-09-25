package source.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapter")
public class Chapter extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Lesson.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ChapterId", nullable = false)
    private List<Lesson> lessons;

    @Builder
    public Chapter(String id, Date createTime, Date updateTime, String name, List<Lesson> lessons) {
        super(id, createTime, updateTime);
        this.name = name;
        this.lessons = lessons;
    }
}
