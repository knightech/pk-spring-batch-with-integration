package com.example.model;

public class Person {

    private String name;

    public Person(String firstName) {
        name = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }

}