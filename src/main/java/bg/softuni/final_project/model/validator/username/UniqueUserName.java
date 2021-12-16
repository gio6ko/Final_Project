package bg.softuni.final_project.model.validator.username;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserNameValidator.class)
public @interface UniqueUserName {

    String message() default "Username is taken.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
