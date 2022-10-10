package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseRequestDto extends BasicRequest {

    private String name;

    private String author;

    private String image;

    private String description;

    private String level;

    private String price;

    private List<ChapterDto> chapters;

    private List<TargetDto> targets;

    private List<RequestDto> requests;
}