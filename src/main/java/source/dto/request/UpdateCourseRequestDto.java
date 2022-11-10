package source.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.ChapterDto;
import source.entity.Request;
import source.entity.Target;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCourseRequestDto extends BasicRequest {

    private String id;

    private String name;

    private String author;

    private String image;

    private String description;

    private String level;

    private String price;

    private List<ChapterDto> chapters;

    private List<Target> targets;

    private List<Request> requests;
}