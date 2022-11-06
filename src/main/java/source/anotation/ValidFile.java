package source.anotation;

import source.anotation.handler.FileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileValidator.class})
public @interface ValidFile {

    public static String IMAGE = "image";

    public static String AUDIO = "audio";

    String message() default "{message.key}";

    String type() default IMAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
