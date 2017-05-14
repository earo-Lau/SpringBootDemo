package com.earo.test.model;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lauearo on 03/05/2017.
 */
@Entity
@NamedQuery(name="Person.withNameAndAddressNameQuery",
        query = "select p from Person p where p.name=?1 and p.address=?2")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String name;
    private Integer age;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
        super();
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Person(String name, int age, String address, Long id){
        super();

        this.name=name;
        this.age=age;
        this.address=address;
        this.id=id;
    }
}
