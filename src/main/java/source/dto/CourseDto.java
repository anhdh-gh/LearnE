package source.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.Request;
import source.entity.Target;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto extends BaseDto{

    private String name;

    private String author;

    private String image;

    private String description;

    private Long numberOfPeople;

    private String level;

    private String price;

    private List<ChapterDto> chapters;

    private List<Target> targets;

    private List<Request> requests;
}
