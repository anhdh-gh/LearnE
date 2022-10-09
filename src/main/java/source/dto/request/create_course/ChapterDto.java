package source.dto.request.create_course;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterDto {

    private String name;

    private List<LessonDto> lessons;
}
