package src.model.dao;

import java.util.List;

import src.model.bean.Person;

public interface IPersonDAO {
	boolean save(Person person);
	boolean update(int personId, Person person);
	boolean delete(int personId);
	Person getByEmail(String email);
	Person getById(int personId);
	List<Person> listPeople();
}
