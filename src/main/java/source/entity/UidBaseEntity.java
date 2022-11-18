package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass // Class cha không phải là entity. https://techmaster.vn/posts/36499/hibernate-inheritance-mapping
@Data  // All together now: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor!
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UidBaseEntity extends BaseEntity {

    @Id
    @Column(name = "Id")
    protected String id;

    @Override
    protected void init() {
        super.init();
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}