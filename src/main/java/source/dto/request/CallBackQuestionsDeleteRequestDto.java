package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.enumeration.Provider;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CallBackQuestionsDeleteRequestDto extends BasicRequest {

    private String referenceId;

    private Provider provider;
}
