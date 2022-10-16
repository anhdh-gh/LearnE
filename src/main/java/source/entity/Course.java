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
@Table(name = "course", schema = "public")
@SuperBuilder
public class Course extends UidBaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Author")
    private String author;

    @Column(name = "Image")
    private String image;

    @Column(name = "Description")
    private String description;

    @Column(name = "Level")
    private String level;

    @Column(name = "Price")
    private String price;

    @OneToMany(targetEntity = Chapter.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseId", nullable = false)
    @OrderBy(value = "id asc")
    private List<Chapter> chapters;

    @OneToMany(targetEntity = Target.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseId", nullable = false)
    private List<Target> targets;

    @OneToMany(targetEntity = Request.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseId", nullable = false)
    private List<Request> requests;
}