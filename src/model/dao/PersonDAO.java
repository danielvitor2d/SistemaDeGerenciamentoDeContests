package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.bean.Person;
import model.bean.PersonType;

public class PersonDAO implements IPersonDAO {

	@Override
	public boolean save(Person person) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("insert into manage_contests.person (name, age, email,	phone, university, personType) values  (?, ?, ?, ?, ?, ?::manage_contests.personType)");
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setString(4, person.getPhone());
			preparedStatement.setString(5, person.getUniversity());
			preparedStatement.setString(6, person.getPersonType().toString());

			preparedStatement.execute();

			result = true;
		} catch (SQLException e) {
			System.out.println("Erro ao salvar!\n" + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}

		return result;
	}

	@Override
	public boolean update(int personId, Person person) {
		return false;
	}

	@Override
	public boolean delete(int personId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		int result = 0;

		try {
			stmt = con.prepareStatement("delete from manage_contests.person as p where p.personId = (?)");
			stmt.setInt(1, personId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar!\n" + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		return (result == 1 ? true : false);
	}

	@Override
	public Person getByEmail(String email) {
		return null;
	}

	@Override
	public Person getById(int personId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Person person = null;

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.person as p where p.personId = (?)");
			preparedStatement.setInt(1, personId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				person = new Person();
				person.setPersonId(resultSet.getInt("personId"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				person.setEmail(resultSet.getString("email"));
				person.setPhone(resultSet.getString("phone"));
				person.setUniversity(resultSet.getString("university"));
				person.setPersonType(PersonType.valueOf(resultSet.getString("personType")));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar!\n" + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return person;
	}

	@Override
	public List<Person> listPeople() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Person> people = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.person");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Person person = new Person();

				person.setPersonId(resultSet.getInt("personId"));
				person.setName(resultSet.getString("name"));
				person.setAge(resultSet.getInt("age"));
				person.setEmail(resultSet.getString("email"));
				person.setPhone(resultSet.getString("phone"));
				person.setUniversity(resultSet.getString("university"));
				person.setPersonType(PersonType.valueOf(resultSet.getString("personType")));

				people.add(person);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar!\n" + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return people;
	}

}
