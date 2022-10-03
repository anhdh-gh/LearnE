package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCourseRequestDto extends BasicRequest {

    private String name;

    private String author;

    private String image;

    private long numberOfPeople;

    private String description;

    private List<ExtraDataDto> extraDataList;

    private List<ChapterDto> chapters;

}
