package com.example.demo.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "email is mandatory")
    private String email;
    private String firstname;
    private String lastname;

    public Person() { }

    public Person(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Person(Long id, String username, String email, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
