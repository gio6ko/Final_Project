package bg.softuni.final_project.service;

import bg.softuni.final_project.model.binding.UserEditBindingModel;
import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.service.UserServiceModel;

public interface UserService {

    void initialiseUsersAndRoles();

    void registerAndLoginUser(UserServiceModel serviceModel);

    UserEntity findByUsername(String username);

    boolean isUserNameFree(String username);

    boolean isPasswordMatch(String password, String confirmPassword);

    void editUser(String username, UserEditBindingModel userEditBindingModel);
}
