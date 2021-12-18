package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.entity.UserRoleEntity;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import bg.softuni.final_project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ShopUserServiceImplTest {

    private UserEntity user;

    private UserRoleEntity adminRole, userRole;


    private ShopUserServiceImpl testService;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void init() {

        testService = new ShopUserServiceImpl(userRepository);

        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        user = new UserEntity();
        user.setUsername("pesho");
        user.setEmail("pesho@abv.bg");
        user.setPassword("123");
        user.setRoles(Set.of(adminRole, userRole));
    }


    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> testService.loadUserByUsername("error@abv.bg")
        );
    }

    @Test
    void testUserFound() {

        Mockito.when(userRepository.findByUsername(user.getUsername())).
                thenReturn(Optional.of(user));

        ShopUser expectedShopUser = testService.loadUserByUsername(user.getUsername());


        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        String actualRoles = expectedShopUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.joining(", "));

        Assertions.assertEquals(expectedShopUser.getUsername(), user.getUsername());
        Assertions.assertEquals(expectedRoles, actualRoles);
    }
}