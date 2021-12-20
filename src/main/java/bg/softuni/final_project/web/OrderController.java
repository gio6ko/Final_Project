package bg.softuni.final_project.web;


import bg.softuni.final_project.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/add")
    public String addOrder(Principal principal, @RequestParam(value = "sum") Integer sum,
                           @RequestParam(value = "zeroValue") Integer zeroValue,
                           RedirectAttributes redirectAttributes) {


        if (sum == 0) {

            redirectAttributes.addFlashAttribute("zeroSum", true);

            return "redirect:/cart";
        } else if (zeroValue == 1) {
            redirectAttributes.addFlashAttribute("valueIsZero", true);
            return "redirect:/cart";
        }
        orderService.createOrder(principal.getName(), sum);

        return "redirect:/";
    }
}
