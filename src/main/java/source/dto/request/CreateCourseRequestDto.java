package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.ChapterDto;
import source.dto.RequestDto;
import source.dto.TargetDto;
import source.entity.Chapter;
import source.entity.Request;
import source.entity.Target;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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