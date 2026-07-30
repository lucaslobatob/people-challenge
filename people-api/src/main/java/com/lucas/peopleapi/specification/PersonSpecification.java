package com.lucas.peopleapi.specification;

import com.lucas.peopleapi.model.Person;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {
    public static Specification<Person> filter(
            String name,
            String document
    ) {

        return (root, query, criteriaBuilder) -> {

            var predicate = criteriaBuilder.conjunction();


            if (name != null && !name.isBlank()) {

                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get("fullName")
                                ),
                                "%" + name.toLowerCase() + "%"
                        )
                );

            }


            if (document != null && !document.isBlank()) {

                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(
                                root.get("document"),
                                "%" + document + "%"
                        )
                );
            }
            return predicate;
        };
    }
}
