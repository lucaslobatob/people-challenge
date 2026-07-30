package com.lucas.peopleapi.repository;

import com.lucas.peopleapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    boolean existsByDocument(String document);

    boolean existsByEmail(String email);

    boolean existsByDocumentAndIdNot(String document, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);
}
