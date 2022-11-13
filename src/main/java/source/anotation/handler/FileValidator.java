package source.anotation.handler;

import org.springframework.web.multipart.MultipartFile;
import source.anotation.ValidFile;
import source.constant.MimeTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private String type;

    @Override
    public void initialize(ValidFile constraintAnnotation) {
        type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if(file == null) {
            return true;
        }

        return isSupportedContentType(file.getContentType());
    }

    private boolean isSupportedContentType(String contentType) {
        List<String> typesValid =
            ValidFile.IMAGE.equals(type) ? Arrays.asList(
                // Image
                MimeTypes.MIME_IMAGE_BMP,
                MimeTypes.MIME_IMAGE_CGM,
                MimeTypes.MIME_IMAGE_GIF,
                MimeTypes.MIME_IMAGE_IEF,
                MimeTypes.MIME_IMAGE_JPEG,
                MimeTypes.MIME_IMAGE_JPG,
                MimeTypes.MIME_IMAGE_TIFF,
                MimeTypes.MIME_IMAGE_PNG,
                MimeTypes.MIME_IMAGE_SVG_XML,
                MimeTypes.MIME_IMAGE_VND_DJVU,
                MimeTypes.MIME_IMAGE_WAP_WBMP,
                MimeTypes.MIME_IMAGE_X_CMU_RASTER,
                MimeTypes.MIME_IMAGE_X_ICON,
                MimeTypes.MIME_IMAGE_X_PORTABLE_ANYMAP,
                MimeTypes.MIME_IMAGE_X_PORTABLE_BITMAP,
                MimeTypes.MIME_IMAGE_X_PORTABLE_GRAYMAP,
                MimeTypes.MIME_IMAGE_X_PORTABLE_PIXMAP,
                MimeTypes.MIME_IMAGE_X_RGB
            ) : ValidFile.AUDIO.equals(type) ? Arrays.asList(
                // Audio
                MimeTypes.MIME_AUDIO_BASIC,
                MimeTypes.MIME_AUDIO_MIDI,
                MimeTypes.MIME_AUDIO_MPEG,
                MimeTypes.MIME_AUDIO_X_AIFF,
                MimeTypes.MIME_AUDIO_X_MPEGURL,
                MimeTypes.MIME_AUDIO_X_PN_REALAUDIO,
                MimeTypes.MIME_AUDIO_X_WAV
            ) : new ArrayList<>();

        return typesValid.stream().anyMatch(type -> type.equals(contentType));
    }
}