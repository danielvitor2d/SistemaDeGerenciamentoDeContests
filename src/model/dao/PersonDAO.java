package src.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.connection.ConnectionFactory;
import src.model.bean.Person;
import src.model.bean.PersonType;

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

			result = preparedStatement.executeUpdate() == 1;

			result = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}

		return result;
	}

	@Override
	public boolean update(int personId, Person person) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("update manage_contests.person set name = ?, age = ?, email = ?, phone = ?, university = ?, personType = ?::manage_contests.personType WHERE personId = ?");
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.setString(4, person.getPhone());
			preparedStatement.setString(5, person.getUniversity());
			preparedStatement.setString(6, person.getPersonType().toString());
			preparedStatement.setInt(7, person.getPersonId());

			result = preparedStatement.executeUpdate() == 1;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public boolean delete(int personId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		boolean result = false;

		try {
			stmt = con.prepareStatement("delete from manage_contests.person as p where p.personId = (?)");
			stmt.setInt(1, personId);

			result = (stmt.executeUpdate() == 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		return result;
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
			System.out.println(e.getMessage());
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
			preparedStatement = connection.prepareStatement("select * from manage_contests.person as p order by p.personId");
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
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return people;
	}

}
