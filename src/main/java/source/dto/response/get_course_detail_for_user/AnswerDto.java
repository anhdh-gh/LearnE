package source.dto.response.get_course_detail_for_user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.UidBaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerDto extends UidBaseEntity {

    private String text;

    @JsonProperty("isCorrect")
    private boolean isCorrect;

    @JsonProperty("isChoice")
    private Boolean isChoice;
}