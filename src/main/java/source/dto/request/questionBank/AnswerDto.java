package source.dto.request.questionBank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.BaseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AnswerDto extends BaseDto {

    private String text;

    private String audio;
}