package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include. NON_NULL)
    protected String id;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "CreateTime")
    protected Date createTime;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "UpdateTime")
    protected Date updateTime;

    @PrePersist // Thực thi trước khi entity được persist (được lưu vào database) bởi method persist()
    protected void init() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        this.createTime = new Date();
    }

    @PreUpdate // Thực thi trước khi entity được update
    protected void updateTime() {
        this.updateTime = new Date();
    }
}