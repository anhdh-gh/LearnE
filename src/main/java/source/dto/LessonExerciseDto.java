package source.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.enumeration.Provider;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonExerciseDto {

    private String referenceId;

    private Provider provider;
}
