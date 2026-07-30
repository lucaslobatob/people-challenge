package com.lucas.peopleapi.controller;

import com.lucas.peopleapi.model.Person;
import com.lucas.peopleapi.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Page<Person> getAllPeople(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String document,
            Pageable pageable
    ) {
        return personService.findAll(name, document, pageable);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PatchMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @Valid @RequestBody Person person){
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
