package source.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "Nation")
    private String nation;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "City")
    private String city;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "Province")
    private String province;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "District")
    private String district;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "Street")
    private String street;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Column(name = "NumberHouse")
    private String numberHouse;

    @Builder
    public Address(String id, Date createTime, Date updateTime, String nation, String city, String province, String district, String street, String numberHouse) {
        super(id, createTime, updateTime);
        this.nation = nation;
        this.city = city;
        this.province = province;
        this.district = district;
        this.street = street;
        this.numberHouse = numberHouse;
    }
}