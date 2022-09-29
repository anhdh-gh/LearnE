package source.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.enumeration.ExtraDataKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtraDataDto {

    private ExtraDataKey extraDataKey;

    private String extraDataValue;
}
