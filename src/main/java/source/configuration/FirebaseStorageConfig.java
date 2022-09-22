package source.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import source.constant.FirebaseStorageConstant;

import java.io.IOException;

@Configuration
public class FirebaseStorageConfig {

    @Bean
    public Storage storage() throws IOException {
        ClassPathResource serviceAccount = new ClassPathResource("firebase-service-accounts.json");
        Storage storage = StorageOptions
            .newBuilder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
            .setProjectId(FirebaseStorageConstant.PROJECT_ID)
            .build()
            .getService();

        return storage;
    }
}
