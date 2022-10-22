package source.dto.request.studyset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.BaseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WordCardDto extends BaseDto {

    private String key;

    private String value;

    private String image;
}