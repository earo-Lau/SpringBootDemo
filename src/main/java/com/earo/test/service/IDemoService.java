package com.earo.test.service;

import com.earo.test.model.Person;

/**
 * Created by lauearo on 12/05/2017.
 */
public interface IDemoService {
    Person saveWithRollBack(Person person);
    Person saveWithoutRollBack(Person person);
}
