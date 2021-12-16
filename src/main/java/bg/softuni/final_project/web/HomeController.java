package bg.softuni.final_project.web;

import bg.softuni.final_project.service.BookService;
import bg.softuni.final_project.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    private final BookService bookService;

    public HomeController(BookService bookService, CartService cartService) {
        super(cartService);
        this.bookService = bookService;
    }

    @GetMapping
    public String home(Model model) {

        model.addAttribute("books", bookService.getFirstEight());
        return "index";
    }


}