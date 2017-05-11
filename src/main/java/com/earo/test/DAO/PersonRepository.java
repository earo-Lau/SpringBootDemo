package com.earo.test.DAO;

import com.earo.test.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by lauearo on 09/05/2017.
 */
@RepositoryRestResource(path = "people")
public interface PersonRepository extends CustomRepository<Person, Long> {
    List<Person> findByAddress(String address);
    @RestResource(path = "byNameAndAddress", rel = "byNameAndAddress")
    Person findByNameAndAddress(@Param("name") String name, @Param("address") String address);

    @Query(value = "select p from Person p where p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);
    List<Person> withNameAndAddressNameQuery(String name, String address);

    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    Person findByNameStartsWith(@Param("name") String name);
}
