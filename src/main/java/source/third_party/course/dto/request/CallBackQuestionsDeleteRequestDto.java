package source.third_party.course.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;
import source.third_party.course.constant.Provider;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallBackQuestionsDeleteRequestDto extends BasicRequest {

    private String referenceId;

    private Provider provider;
}
