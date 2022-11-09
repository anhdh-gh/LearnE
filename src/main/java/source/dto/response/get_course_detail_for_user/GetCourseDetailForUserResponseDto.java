package source.dto.response.get_course_detail_for_user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.Course;
import source.entity.enumeration.StatusType;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCourseDetailForUserResponseDto extends Course {

    private Long numberOfPeople;

    @JsonProperty("chapters")
    private List<ChapterDto> chapterDtos;

    private StatusType status;
}
