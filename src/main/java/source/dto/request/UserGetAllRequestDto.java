package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.constant.ErrorCodeConstant;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetAllRequestDto extends BasicRequest{

    @Min(value = 0, message = ErrorCodeConstant.PAGE_IS_GREATER_THAN_MINUS_ONE_400029)
    private int page;

    @Min(value =0, message = ErrorCodeConstant.SIZE_IS_GREATER_THAN_MINUS_ONE_400030)
    private int size;
}
