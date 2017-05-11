package com.earo.test.DAO;

import com.earo.test.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lauearo on 09/05/2017.
 */
public interface PersonRepository extends CustomRepository<Person, Long> {
    List<Person> findByAddress(String address);
    Person findByNameAndAddress(String name, String address);

    @Query(value = "select p from Person p where p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    List<Person> withNameAndAddressNameQuery(String name, String address);
}
