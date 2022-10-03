package source.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

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
    private List<Chapter> chapters;

    @OneToMany(targetEntity = Target.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseId", nullable = false)
    private List<Target> targets;

    @OneToMany(targetEntity = Request.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseId", nullable = false)
    private List<Request> requests;

    @Builder
    public Course(String id, Date createTime, Date updateTime, String name, String author, String image, String description, String level, String price, List<Chapter> chapters, List<Target> targets, List<Request> requests) {
        super(id, createTime, updateTime);
        this.name = name;
        this.author = author;
        this.image = image;
        this.description = description;
        this.level = level;
        this.price = price;
        this.chapters = chapters;
        this.targets = targets;
        this.requests = requests;
    }
}