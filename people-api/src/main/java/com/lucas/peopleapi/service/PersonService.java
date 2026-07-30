package com.lucas.peopleapi.service;

import com.lucas.peopleapi.model.Person;
import com.lucas.peopleapi.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person person) {
        Person existingPerson = findById(id);

        if (person.getFullName() != null) {
        existingPerson.setFullName(person.getFullName());
        }

        if (person.getDocument() != null) {
            existingPerson.setDocument(person.getDocument());
        }

        if (person.getPhone() != null) {
            existingPerson.setPhone(person.getPhone());
        }

        if (person.getEmail() != null) {
            existingPerson.setEmail(person.getEmail());
        }

        return personRepository.save(existingPerson);
    }

    public void deletePerson(Long id) {
        Person person = findById(id);
        personRepository.delete(person);
    }
}
