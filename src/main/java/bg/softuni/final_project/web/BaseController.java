package bg.softuni.final_project.web;

import bg.softuni.final_project.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public abstract class BaseController {

    private final CartService cartService;



    protected BaseController(CartService cartService) {
        this.cartService = cartService;
    }


    @ModelAttribute("items")
    public Long itemsInCart(Principal principal) {
        if (principal == null) {
            return 0L;
        }
        return
                cartService.getRepoCount(principal.getName());
    }
}
