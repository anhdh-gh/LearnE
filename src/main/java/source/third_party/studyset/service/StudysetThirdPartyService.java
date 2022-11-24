package source.third_party.studyset.service;

import source.dto.response.BaseResponse;
import source.third_party.studyset.dto.request.StudysetGetByIdsRequestDto;

public interface StudysetThirdPartyService {

    BaseResponse getStudysetByStudysetIds(StudysetGetByIdsRequestDto request) throws Exception;
}
