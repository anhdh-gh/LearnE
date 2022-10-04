package source.entity;

import lombok.*;
import source.entity.enumeration.StatusType;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessonstatus")
public class LessonStatus extends BaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", columnDefinition = "varchar(255) default 'UNFINISHED'")
    private StatusType status;

    @ManyToOne(targetEntity = Lesson.class)
    @JoinColumn (name = "LessonId", nullable = false)
    private Lesson lesson;

    @Builder
    public LessonStatus(String id, Date createTime, Date updateTime, String userId, StatusType status, Lesson lesson) {
        super(id, createTime, updateTime);
        this.userId = userId;
        this.status = status;
        this.lesson = lesson;
    }
}
