package bg.softuni.final_project.web;


import bg.softuni.final_project.model.entity.CartItem;
import bg.softuni.final_project.service.CartService;
import bg.softuni.final_project.service.impl.ShopUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {


    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/cart")
    public String cartPage(Model model, Principal principal) {

        List<CartItem> allByUsername = cartService.findAllByUsername(principal.getName());

        if (allByUsername.isEmpty()) {
            return "empty";
        }

        model.addAttribute("cartItems", allByUsername);
        return "cart";
    }

    @GetMapping("/cart/{id}")
    public String addOfferToCart(@PathVariable Long id, @AuthenticationPrincipal ShopUser principal, RedirectAttributes attributes) {

        boolean added = cartService.addBookToCart(id, principal.getIdentifier());

        return "redirect:/";
    }

    @DeleteMapping("/cart/{id}")
    public String deleteItem(@PathVariable Long id) {


        cartService.deleteItem(id);

        return "redirect:/cart";
    }

    @ModelAttribute("zeroSum")
    public boolean zeroSum() {
        return false;
    }
}
