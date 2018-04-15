package app.service;

import app.domain.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();
    void create(Person person);
    Person findById(long id);
    List<Person> findByCountry(String country);

    List<Person> findAllByAddressCountry(String country);
}
