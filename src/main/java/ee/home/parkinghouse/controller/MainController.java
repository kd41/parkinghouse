package ee.home.parkinghouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(Model model) {
        return "main";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("name", "Graziano");
        return "main";
    }

}
