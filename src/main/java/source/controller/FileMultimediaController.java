package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.anotation.LogsActivityAnnotation;
import source.constant.RequestKeyConstant;
import source.constant.RouterConstant;
import source.dto.request.FilesUploadRequestDto;
import source.dto.request.UserDeleteAvatarRequestDto;
import source.dto.request.UserDownloadAvatarRequestDto;
import source.dto.request.UserUploadAvatarRequestDto;
import source.dto.response.BaseResponse;
import source.service.FileInformationService;
import source.service.UserMultimediaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class FileMultimediaController {

    @Autowired
    private FileInformationService fileInformationService;

    @PostMapping(RouterConstant.FILE_UPLOAD)
    public BaseResponse uploadFile(@Valid FilesUploadRequestDto request) throws Exception {
        return fileInformationService.uploadFile(request);
    }
}