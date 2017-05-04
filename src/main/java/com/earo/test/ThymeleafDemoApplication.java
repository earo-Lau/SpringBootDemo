package com.earo.test;

import com.earo.test.model.Person;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lauearo on 03/05/2017.
 */
@Controller
@SpringBootApplication
public class ThymeleafDemoApplication {
    @RequestMapping("/thymeleafTest")
    public String index(Model model){
        Person single = new Person("user1", 33);

        List<Person> pList = new ArrayList<Person>();
        pList.add(new Person("xx", 11));
        pList.add(new Person("yy", 22));
        pList.add(new Person("zz", 33));

        model.addAttribute("singlePerson", single);
        model.addAttribute("people",pList);

        return "index";
    }

    public static void main(String[] args){
        new SpringApplicationBuilder(ThymeleafDemoApplication.class)
                .run(args);
    }
}
