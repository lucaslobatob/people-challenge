package com.lucas.peopleapi.service;

import com.lucas.peopleapi.exception.DuplicateResourceException;
import com.lucas.peopleapi.model.Person;
import com.lucas.peopleapi.repository.PersonRepository;
import com.lucas.peopleapi.specification.PersonSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Person> findAll(String name, String document, Pageable pageable) {
        Specification<Person> specification = PersonSpecification.filter(name, document);

        return personRepository.findAll(specification, pageable);
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }

    public Person createPerson(Person person) {
        validateDuplicate(person);
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person person) {
        Person existingPerson = findById(id);
        validateDuplicateOnUpdate(id, person);

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

    private void validateDuplicate(Person person) {
        if (personRepository.existsByDocument(person.getDocument())) {
            throw new DuplicateResourceException(
                    "CPF/CNPJ already registered."
            );
        }

        if (personRepository.existsByEmail(person.getEmail())) {
            throw new DuplicateResourceException(
                    "E-mail already registered."
            );
        }
    }
    private void validateDuplicateOnUpdate(Long id, Person person) {

        if (person.getDocument() != null &&
                personRepository.existsByDocumentAndIdNot(
                        person.getDocument(),
                        id
                )) {

            throw new DuplicateResourceException(
                    "CPF/CNPJ already registered."
            );
        }


        if (person.getEmail() != null &&
                personRepository.existsByEmailAndIdNot(
                        person.getEmail(),
                        id
                )) {

            throw new DuplicateResourceException(
                    "E-mail already registered."
            );
        }
    }
}
