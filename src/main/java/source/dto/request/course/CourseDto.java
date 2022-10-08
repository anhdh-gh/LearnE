package source.dto.request.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.BaseDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto extends BaseDto {

    private String name;

    private String author;

    private String image;

    private String description;

    private Long numberOfPeople;

    private String level;

    private String price;

    private List<ChapterDto> chapters;

    private List<TargetDto> targets;

    private List<RequestDto> requests;
}
