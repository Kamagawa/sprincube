package sprincube.bff.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UiController {

    private final Logger logger = LoggerFactory.getLogger(UiController.class);

    String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute("message", this.message);
        return "welcome";
    }
}
