package source.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
public class LessonStatus extends BaseEntity {

    @Column(name = "UserId")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", columnDefinition = "varchar(255) default 'UNFINISHED'")
    private StatusType status;

    @ManyToOne(targetEntity = Lesson.class)
    @JoinColumn (name = "LessonId", nullable = false)
    private Lesson lesson;
}
