package com.lucas.peopleapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String fullName;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String document;

    @Column(nullable = false)
    @NotBlank
    private String phone;

    @Column(nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    public Person(){
    }

    public Person(String fullName, String document, String phone, String email) {
        this.fullName = fullName;
        this.document = document;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
