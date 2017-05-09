package com.earo.test;

import com.earo.test.model.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lauearo on 08/05/2017.
 */
@RestController
public class AngularActionDemoApplication {
    @RequestMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Person search(String personName){
        return new Person(personName, 30, "Kap load");
    }
}
