package model.dao;

import java.util.List;

import model.bean.Person;

public interface IPersonDAO {
	void save(Person person);
	void update(int personId, Person person);
	void delete(int personId);
	Person getByEmail(String email);
	Person getById(int personId);
	List<Person> listPerson();
}
