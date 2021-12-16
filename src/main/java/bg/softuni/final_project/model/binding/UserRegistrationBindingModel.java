package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.validator.password.PasswordEqual;
import bg.softuni.final_project.model.validator.username.UniqueUserName;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordEqual
public class UserRegistrationBindingModel {


    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;


    @NotBlank
    @UniqueUserName
    @Size(min = 3, max = 20)
    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }


    @NotBlank
    @Size(min = 3, max = 20)
    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }


    @NotBlank
    @Size(min = 3, max = 20)
    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 20)
    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 20)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @Email
    @NotBlank
    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }


//    @AssertTrue(message = "Passwords should match")
//    public boolean isPasswordEqual() {
//        return this.password != null && this.password.equals(this.confirmPassword);
//    }
}
