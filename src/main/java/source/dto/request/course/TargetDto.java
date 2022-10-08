package source.dto.request.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.BaseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetDto extends BaseDto {
    private String text;
}
