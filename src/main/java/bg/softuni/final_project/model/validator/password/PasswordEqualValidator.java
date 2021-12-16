package bg.softuni.final_project.model.validator.password;

import bg.softuni.final_project.model.binding.UserRegistrationBindingModel;
import bg.softuni.final_project.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordEqualValidator implements ConstraintValidator<PasswordEqual, UserRegistrationBindingModel> {


    private final UserService userService;

    public PasswordEqualValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean isValid(UserRegistrationBindingModel bindingModel, ConstraintValidatorContext context) {

        boolean isValid = userService.isPasswordMatch(bindingModel.getPassword(), bindingModel.getConfirmPassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("confirmPassword").addConstraintViolation();
        }
        return isValid;
    }
}
