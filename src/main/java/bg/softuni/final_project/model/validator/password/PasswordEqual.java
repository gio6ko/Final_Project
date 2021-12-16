package bg.softuni.final_project.model.validator.password;


import bg.softuni.final_project.model.validator.username.UniqueUserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordEqualValidator.class)
public @interface PasswordEqual {


    String message() default "Passwords must match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
