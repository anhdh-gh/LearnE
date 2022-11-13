package source.third_party.firebase_storage.service;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import source.constant.FirebaseStorageConstant;
import source.dto.response.BaseResponse;
import source.exception.BusinessErrors;
import source.third_party.firebase_storage.dto.request.FirebaseStorageRequestDto;
import source.third_party.firebase_storage.dto.request.FirebaseUploadFileRequestDto;
import source.third_party.firebase_storage.dto.response.FirebaseUploadFileResponseDto;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    @Autowired
    private Storage storage;

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }

    private String generateFileName(MultipartFile multipartFile, String fileName) {
        return fileName + "." + getExtension(multipartFile.getOriginalFilename());
    }

    @Override
    public FirebaseUploadFileResponseDto uploadFile(FirebaseUploadFileRequestDto firebaseUploadFileRequestDto) throws Exception {
        String fileNameSave = generateFileName(firebaseUploadFileRequestDto.getMultipartFile(), firebaseUploadFileRequestDto.getPath());
        BlobId blobId = BlobId.of(FirebaseStorageConstant.BUCKET, fileNameSave);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
            .setContentType(firebaseUploadFileRequestDto.getMultipartFile().getContentType())
            .build();
        storage.create(blobInfo, firebaseUploadFileRequestDto.getMultipartFile().getBytes());
        return FirebaseUploadFileResponseDto
            .builder()
            .url("https://firebasestorage.googleapis.com/v0/b/" + FirebaseStorageConstant.BUCKET + "/o/" + URLEncoder.encode(fileNameSave, StandardCharsets.UTF_8.name()) + "?alt=media")
            .extension(getExtension(firebaseUploadFileRequestDto.getMultipartFile().getOriginalFilename()))
            .build();
    }


    // Filename phải có cả extension (.png, .jpg, ...)
    @Override
    public ResponseEntity<Object> downloadFile(FirebaseStorageRequestDto firebaseStorageRequestDto) throws Exception {
        Blob blob = storage.get(BlobId.of(FirebaseStorageConstant.BUCKET, firebaseStorageRequestDto.getPath()));
        if(blob == null) {
            return ResponseEntity
                .ok()
                .header("Content-type", "application/json")
                .body(BaseResponse.ofFailed(firebaseStorageRequestDto.getRequestId(), BusinessErrors.BAD_REQUEST, "Download file fail"));
        }
        ReadChannel reader = blob.reader();
        InputStream inputStream = Channels.newInputStream(reader);
        byte[] content = null;
        content = IOUtils.toByteArray(inputStream);
        ByteArrayResource byteArrayResource = new ByteArrayResource(content);
        return ResponseEntity
            .ok()
            .contentLength(byteArrayResource.contentLength())
            .header("Content-type", "application/octet-stream")
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + firebaseStorageRequestDto.getFileName() + "\"")
            .body(byteArrayResource);
    }

    // Filename phải có cả extension (.png, .jpg, ...)
    @Override
    public boolean deleteFile(FirebaseStorageRequestDto firebaseStorageRequestDto) throws Exception {
        Blob blob = storage.get(BlobId.of(FirebaseStorageConstant.BUCKET, firebaseStorageRequestDto.getPath()));
        if(blob == null) {
            return false;
        }
        return blob.delete();
    }
}