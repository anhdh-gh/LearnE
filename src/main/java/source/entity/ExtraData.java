package source.entity;

import lombok.*;
import source.entity.enumeration.ExtraDataKey;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "extradata")
public class ExtraData extends BaseEntity {

    @Column(name = "ExtraDataKey")
    @Enumerated(EnumType.STRING)
    private ExtraDataKey extraDataKey;

    @Column(name = "ExtraDataValue")
    private String extraDataValue;

    @Builder
    public ExtraData(String id, Date createTime, Date updateTime, ExtraDataKey extraDataKey, String extraDataValue) {
        super(id, createTime, updateTime);
        this.extraDataKey = extraDataKey;
        this.extraDataValue = extraDataValue;
    }
}
