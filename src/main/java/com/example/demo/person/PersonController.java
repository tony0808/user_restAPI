package com.example.demo.person;

import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {
    private final PersonRepository personRepository;
    private final PersonModelAssembler assembler;

    public PersonController(PersonRepository personRepository, PersonModelAssembler assembler) {
        this.personRepository = personRepository;
        this.assembler = assembler;
    }

    @GetMapping("/people")
    CollectionModel<EntityModel<Person>> all() {
        List<EntityModel<Person>> people = personRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(people, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @PostMapping("/person")
    Person newPerson(@RequestBody @Valid Person newPerson) {
        return personRepository.save(newPerson);
    }

    @GetMapping("/person/{id}")
    EntityModel<Person> one(@PathVariable Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return assembler.toModel(person);
    }

    @PutMapping("/person/{id}/username")
    Person updatePersonUsername(@RequestBody Person newPerson, @PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setUsername(newPerson.getUsername());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    @PutMapping("/person/{id}/email")
    Person updatePersonEmail(@RequestBody Person newPerson, @PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setEmail(newPerson.getEmail());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    @PutMapping("/person/{id}/firstname")
    Person updatePersonFirstname(@RequestBody Person newPerson, @PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setFirstname(newPerson.getFirstname());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    @PutMapping("/person/{id}/lastname")
    Person updatePersonLastname(@RequestBody Person newPerson, @PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setLastname(newPerson.getLastname());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    @PutMapping("/person/{id}")
    Person updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setUsername(newPerson.getUsername());
                    person.setEmail(newPerson.getEmail());
                    person.setFirstname(newPerson.getFirstname());
                    person.setLastname(newPerson.getLastname());
                    return personRepository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return personRepository.save(newPerson);
                });
    }

    @DeleteMapping("/person/{id}")
    void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> validationExceptionHandler (MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
