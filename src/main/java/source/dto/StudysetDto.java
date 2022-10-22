package source.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudysetDto extends BaseDto {

    private String title;

    private String description;

    private String ownerUserId;

    private UserDto ownerUser;

    private List<WordCardDto> wordCards;
}
