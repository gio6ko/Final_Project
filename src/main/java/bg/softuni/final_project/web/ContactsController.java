package bg.softuni.final_project.web;

import bg.softuni.final_project.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsController extends BaseController {

    protected ContactsController(CartService cartService) {
        super(cartService);
    }

    @GetMapping("/contacts")
    public String home() {
        return "contacts";
    }
}
