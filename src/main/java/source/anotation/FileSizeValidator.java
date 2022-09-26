package source.anotation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<ValidFileSize, MultipartFile> {

    private float fileSize;

    @Override
    public void initialize(ValidFileSize constraintAnnotation) {
        fileSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if(file.getSize() * 0.001 <= fileSize ) return true;
        return false;
    }
}
