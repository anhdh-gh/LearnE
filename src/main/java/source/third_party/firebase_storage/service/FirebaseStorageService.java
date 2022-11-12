package source.third_party.firebase_storage.service;

import org.springframework.http.ResponseEntity;
import source.third_party.firebase_storage.dto.request.FirebaseStorageRequestDto;
import source.third_party.firebase_storage.dto.request.FirebaseUploadFileRequestDto;
import source.third_party.firebase_storage.dto.response.FirebaseUploadFileResponseDto;

public interface FirebaseStorageService {

    public FirebaseUploadFileResponseDto uploadFile(FirebaseUploadFileRequestDto firebaseUploadFileRequestDto) throws Exception;

    public ResponseEntity<Object> downloadFile(FirebaseStorageRequestDto firebaseStorageRequestDto) throws Exception;

    public boolean deleteFile(FirebaseStorageRequestDto firebaseStorageRequestDto) throws Exception;

    public boolean deleteFolder(FirebaseStorageRequestDto firebaseStorageRequestDto) throws Exception;
}