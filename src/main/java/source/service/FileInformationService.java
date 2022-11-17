package source.service;

import source.dto.request.FilesUploadRequestDto;
import source.dto.response.BaseResponse;

public interface FileInformationService {

    BaseResponse uploadFile(FilesUploadRequestDto request) throws Exception;
}
