package source.anotation;

import source.anotation.handler.FileSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileSizeValidator.class})
public @interface ValidFileSize {
    String message() default "{message.key}";

    float maxSize() default 128;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}