package source.dto.request.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.BaseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDto extends BaseDto {


    private String name;

    private List<LessonDto> lessons;
}
