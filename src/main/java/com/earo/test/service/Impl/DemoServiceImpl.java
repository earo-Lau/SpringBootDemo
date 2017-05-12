package com.earo.test.service.Impl;

import com.earo.test.DAO.PersonRepository;
import com.earo.test.model.Person;
import com.earo.test.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lauearo on 12/05/2017.
 */
@Service
public class DemoServiceImpl implements IDemoService {
    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Person saveWithRollBack(Person person) {
        Person p = personRepository.save(person);

        if(p.getName().equals("AA")){
            throw new IllegalArgumentException("Person is in black list");
        }

        return p;
    }

    @Override
    @Transactional(noRollbackFor = IllegalArgumentException.class)
    public Person saveWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if(p.getName().equals("AA")){
            throw new IllegalArgumentException("Person is in black list");
        }

        return p;
    }

    @Override
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("Cache person(id: " + p.getId().toString() + ").");
        return p;
    }

    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        personRepository.delete(id);
        System.out.println("Person (id: " + id.toString() + ") have been remove from cache.");
    }

    @Override
    @Cacheable(value = "people", key = "#id")
    public Person findOne(Long id) {
        Person p = personRepository.findOne(id);
        System.out.println("Cache person(id: " + p.getId().toString() + ").");
        return p;
    }
}
