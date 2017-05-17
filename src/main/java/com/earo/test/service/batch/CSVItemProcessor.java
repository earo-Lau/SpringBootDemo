package com.earo.test.service.batch;

import com.earo.test.model.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

/**
 * Created by lauearo on 15/05/2017.
 */
public class CSVItemProcessor extends ValidatingItemProcessor<Person> {
    @Override
    public Person process(Person item) throws ValidationException {
        super.process(item);

        item.setAge(item.getAge() + 2);
        return item;
    }


}
