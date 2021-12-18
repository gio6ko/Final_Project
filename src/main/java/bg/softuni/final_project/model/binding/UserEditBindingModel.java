package bg.softuni.final_project.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserEditBindingModel {


    private String firstName;
    private String lastName;
    private String email;


    @Size(min = 3, max = 20, message = "ala bala")
    @NotBlank
    public String getFirstName() {
        return firstName;
    }

    public UserEditBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }


    @NotBlank
    @Size(min = 3, max = 20)
    public String getLastName() {
        return lastName;
    }

    public UserEditBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    @Email
    @NotBlank
    public String getEmail() {
        return email;
    }

    public UserEditBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
