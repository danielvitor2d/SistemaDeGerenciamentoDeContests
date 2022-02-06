package service.person.impl;

import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonRepository;
import service.person.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deleteById(Long personId) {
        personRepository.deleteById(personId);
    }

    @Override
    public Person findById(Long id) throws Exception {
        return personRepository.findById(id).orElseThrow(() -> new Exception());
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
