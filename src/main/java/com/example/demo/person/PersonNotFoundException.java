package com.example.demo.person;

public class PersonNotFoundException extends RuntimeException{
    PersonNotFoundException(Long id) {
        super("Could not find person with id: <" + id + ">.");
    }
}
