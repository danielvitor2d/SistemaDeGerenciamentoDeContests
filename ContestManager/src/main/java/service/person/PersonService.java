package service.person;

import model.person.Person;

import java.util.List;

public interface PersonService {
    Person save(Person person);

    void deleteById(Long personId);

    Person findById(Long id) throws Exception;

    List<Person> findAll();
}
