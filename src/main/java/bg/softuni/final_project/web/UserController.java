package bg.softuni.final_project.web;

import bg.softuni.final_project.model.binding.UserEditBindingModel;
import bg.softuni.final_project.model.binding.UserRegistrationBindingModel;
import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.service.UserServiceModel;
import bg.softuni.final_project.model.view.UserView;
import bg.softuni.final_project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {


        return "login";
    }


    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username,
                              RedirectAttributes redirectAttributes) {


        redirectAttributes.addFlashAttribute("bad_credentials", true)
                .addFlashAttribute("username", username);

        return "redirect:/users/login";
    }


    @GetMapping("/register")
    public String register() {

        return "register";
    }


    @PostMapping("/register")
    public String register(
            @Valid UserRegistrationBindingModel userModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userModel.getPassword().equals(userModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        UserServiceModel serviceModel =
                modelMapper.map(userModel, UserServiceModel.class);

        userService.registerAndLoginUser(serviceModel);

        return "redirect:/";
    }


    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal) {

        UserEntity user = userService.findByUsername(principal.getName());

        model.addAttribute("user", modelMapper.map(user, UserView.class));

        return "profile";
    }

    @PutMapping("/profile")
    public String editUser(@Valid UserEditBindingModel userEditBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditBindingModel", userEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditBindingModel", bindingResult);

            return "redirect:/users/profile";
        }

        userService.editUser(principal.getName(), userEditBindingModel);

        return "redirect:/users/profile";
    }

    @ModelAttribute("userModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }

    @ModelAttribute("userEditBindingModel")
    public UserEditBindingModel userEditBindingModel() {
        return new UserEditBindingModel();
    }

}
