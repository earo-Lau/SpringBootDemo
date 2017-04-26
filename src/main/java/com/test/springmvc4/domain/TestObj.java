package com.test.springmvc4.domain;

/**
 * Created by lauearo on 25/04/2017.
 */
public class TestObj {
    private long id;
    private String name;

    public TestObj() {
        super();
    }

    public TestObj(long id, String name) {
        super();

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
