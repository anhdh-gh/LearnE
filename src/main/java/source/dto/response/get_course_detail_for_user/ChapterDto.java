package source.dto.response.get_course_detail_for_user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.Chapter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ChapterDto extends Chapter {

    @JsonProperty("lessons")
    private List<LessonDto> lessonDtos;
}