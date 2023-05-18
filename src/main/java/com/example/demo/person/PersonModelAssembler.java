package com.example.demo.person;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonModelAssembler {
    public EntityModel<Person> toModel(Person person) {
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).one(person.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).all()).withRel("people"));
    }
}
