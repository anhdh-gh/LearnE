package source.third_party.firebase_storage.service;

import org.springframework.http.ResponseEntity;
import source.third_party.firebase_storage.dto.request.FirebaseStorageRequestDto;
import source.third_party.firebase_storage.dto.request.FirebaseUploadFileRequestDto;
import source.third_party.firebase_storage.dto.response.FirebaseUploadFileResponseDto;

public interface FirebaseStorageService {

    FirebaseUploadFileResponseDto uploadFile(FirebaseUploadFileRequestDto firebaseUploadFileRequestDto) throws Exception;

    ResponseEntity<Object> downloadFile(FirebaseStorageRequestDto firebaseStorageRequestDto) throws Exception;

    boolean deleteFile(FirebaseStorageRequestDto firebaseStorageRequestDto) throws Exception;
}