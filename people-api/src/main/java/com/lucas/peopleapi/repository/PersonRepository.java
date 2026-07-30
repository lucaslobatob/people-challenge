package com.lucas.peopleapi.repository;

import com.lucas.peopleapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
