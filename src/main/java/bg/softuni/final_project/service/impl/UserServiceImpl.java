package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.binding.UserEditBindingModel;
import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.entity.UserRoleEntity;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import bg.softuni.final_project.model.service.UserServiceModel;
import bg.softuni.final_project.repository.UserRepository;
import bg.softuni.final_project.repository.UserRoleRepository;
import bg.softuni.final_project.service.UserService;
import bg.softuni.final_project.web.exception.ObjectNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ShopUserServiceImpl shopUserService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ShopUserServiceImpl shopUserService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.shopUserService = shopUserService;
    }


    @Override
    public void initialiseUsersAndRoles() {

        initializeRoles();
        initializeUsers();
    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("123"))
                    .setFirstName("Georgi")
                    .setLastName("Yazmadzhian")
                    .setEmail("joro@abv.bg");

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);
        }
    }


    @Override
    public void registerAndLoginUser(UserServiceModel userServiceModel) {

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser.
                setUsername(userServiceModel.getUsername()).
                setFirstName(userServiceModel.getFirstName()).
                setLastName(userServiceModel.getLastName()).
                setEmail(userServiceModel.getEmail()).
                setPassword(passwordEncoder.encode(userServiceModel.getPassword())).
                setRoles(Set.of(userRole));

        newUser = userRepository.save(newUser);

        //this is a Spring representation of a user
        UserDetails principal = shopUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserEntity findByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("No such user with username - "
                        + username + "!"));
    }

    @Override
    public boolean isUserNameFree(String username) {

        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean isPasswordMatch(String password, String confirmPassword) {

        return password.equals(confirmPassword);
    }

    @Override
    public void editUser(String username, UserEditBindingModel userEditBindingModel) {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found"));

        user.setFirstName(userEditBindingModel.getFirstName()).setLastName(userEditBindingModel.getLastName())
                .setEmail(userEditBindingModel.getEmail());

        userRepository.save(user);
    }
}
