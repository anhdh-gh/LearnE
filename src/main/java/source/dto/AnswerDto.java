package source.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AnswerDto extends BaseDto {

    private String text;

    private String audio;
}
