package service;

import model.Person;

import java.util.List;

public interface PersonService {
    List<Person> readAll();

    Person read(int id);

    void create(Person person);

    void delete(int id);

    Person readByFirstName(String firstName);
}
