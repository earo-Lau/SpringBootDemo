package com.earo.test;

import com.earo.test.model.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lauearo on 15/05/2017.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        Msg msg = new Msg("Test Title", "Test Content", "extra info, show for admin role only");
        model.addAttribute("msg", msg);

        return "home";
    }
}
