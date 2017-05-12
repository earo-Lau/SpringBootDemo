package com.earo.test;

import com.earo.test.DAO.CustomRepositoryFactoryBean;
import com.earo.test.DAO.PersonRepository;
import com.earo.test.model.Person;
import com.earo.test.service.IDemoService;
import com.sun.xml.internal.rngom.digested.DDataPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lauearo on 10/05/2017.
 */
@RestController
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class DataController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    IDemoService demoService;

    @RequestMapping(value = "/save")
    public Person save(String name, String address, int age){
        Person person = personRepository.save(new Person(name, age, address, null));
        return person;
    }

    @RequestMapping("/query1")
    public List<Person> query1(String address){
        List<Person> peopleList = personRepository.findByAddress(address);
        return peopleList;
    }

    @RequestMapping("/query2")
    public Person query2(String name, String address){
        Person person = personRepository.findByNameAndAddress(name, address);
        return person;
    }

    @RequestMapping("/query3")
    public Person query3(String name, String address){
        Person person = personRepository.withNameAndAddressQuery(name, address);
        return person;
    }

    @RequestMapping("/query4")
    public List<Person> query4(String name, String address){
        List<Person> personList = personRepository.withNameAndAddressNameQuery(name, address);
        return personList;
    }

    @RequestMapping("/sort")
    public List<Person> sort(){
        List<Person> personList = personRepository.findAll(new Sort(Sort.Direction.DESC, "age"));
        return personList;
    }

    @RequestMapping("/page")
    public Page<Person> page(@RequestParam(value = "p", defaultValue = "0") int p,
                             @RequestParam(value = "s", defaultValue = "3") int s){
        Page<Person> personPage = personRepository.findAll(new PageRequest(p, s));
        return personPage;
    }

    @RequestMapping("/auto")
    public Page<Person> auto(Person person){
        Page<Person> personPage = personRepository.findByAuto(person, new PageRequest(0,10));

        return personPage;
    }

    @RequestMapping("/rollback")
    public Person rollBack(Person person){
        return demoService.saveWithRollBack(person);
    }

    @RequestMapping("/noRollback")
    public Person noRollBack(Person person){
        return demoService.saveWithoutRollBack(person);
    }

}
