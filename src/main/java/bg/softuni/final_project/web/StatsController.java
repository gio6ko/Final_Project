package bg.softuni.final_project.web;

import bg.softuni.final_project.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatsController {

    private final StatService statService;


    public StatsController(StatService statService) {
        this.statService = statService;
    }


    @GetMapping("/statistics")
    public ModelAndView statistics(){


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("stats",statService.getStats());
        modelAndView.setViewName("stats");

        return modelAndView;
    }
}