package sprincube.userapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UController {

    private final Logger logger = LoggerFactory.getLogger(UController.class);

    String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute("message", this.message);
        return "welcome";
    }
}
