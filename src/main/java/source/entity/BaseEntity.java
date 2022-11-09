package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass // Class cha không phải là entity. https://techmaster.vn/posts/36499/hibernate-inheritance-mapping
@Data  // All together now: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor!
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {

    @Column(name = "CreateTime")
    protected Date createTime;

    @Column(name = "UpdateTime")
    protected Date updateTime;

    @PrePersist // Thực thi trước khi entity được persist (được lưu vào database) bởi method persist()
    protected void init() {
        this.createTime = new Date();
    }

    @PreUpdate // Thực thi trước khi entity được update
    protected void updateTime() {
        this.updateTime = new Date();
    }
}