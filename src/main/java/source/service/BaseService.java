package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import source.constant.FirebaseStorageConstant;
import source.third_party.firebase_storage.service.FirebaseStorageService;

public class BaseService {

    @Autowired
    protected FirebaseStorageService firebaseStorageService;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected Environment environment;

    protected String getExtensionByUrl(String avatarUrl) {
        String fileName =
            replaceLast(avatarUrl, "\\?alt=media", "")
                .replaceFirst("https://firebasestorage.googleapis.com/v0/b/" + FirebaseStorageConstant.BUCKET + "/o/", "");

        return StringUtils.getFilenameExtension(fileName);
    }

    protected String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}
