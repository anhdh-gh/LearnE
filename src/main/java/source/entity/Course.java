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
@Table(name = "course")
public class Course extends BaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "NumberOfPeople")
    private long numberOfPeople;

    @Column(name = "Description")
    private String description;

    @OneToMany(targetEntity = ExtraData.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseId", nullable = false)
    private List<ExtraData> extraDataList;

    @OneToMany(targetEntity = Chapter.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "CourseId", nullable = false)
    private List<Chapter> chapters;

    @Builder
    public Course(String id, Date createTime, Date updateTime, String name, long numberOfPeople, String description, List<ExtraData> extraDataList, List<Chapter> chapters) {
        super(id, createTime, updateTime);
        this.name = name;
        this.numberOfPeople = numberOfPeople;
        this.description = description;
        this.extraDataList = extraDataList;
        this.chapters = chapters;
    }
}