package source.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass // Class cha không phải là entity. https://techmaster.vn/posts/36499/hibernate-inheritance-mapping
@Data  // All together now: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor!
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @Id
    @Column(name = "Id")
    protected String id;

    @Column(name = "CreateTime")
    protected Date createTime;

    @Column(name = "UpdateTime")
    protected Date updateTime;

    @PrePersist // Thực thi trước khi entity được persist (được lưu vào database) bởi method persist()
    protected void init() {
        this.id = UUID.randomUUID().toString();
        this.createTime = new Date();
    }

    @PreUpdate // Thực thi trước khi entity được update
    protected void updateTime() {
        this.updateTime = new Date();
    }
}