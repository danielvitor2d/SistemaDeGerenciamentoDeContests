package src.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.connection.ConnectionFactory;

import src.model.bean.Problem;

public class ProblemDAO implements IProblemDAO {

  @Override
  public boolean save(Problem problem) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestId) values  (?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, problem.getTitle());
			preparedStatement.setString(2, problem.getDescription());
			preparedStatement.setDouble(3, problem.getTimelimit());
			preparedStatement.setString(4, problem.getSampleInputProblem());
			preparedStatement.setString(5, problem.getSampleOutputProblem());
			preparedStatement.setInt(6, problem.getContestId());

			result = preparedStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}

		return result;
  }

  @Override
  public Problem getById(int problemId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Problem problem = null;

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.problem as p where p.problemId = (?)");
			preparedStatement.setInt(1, problemId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				problem = new Problem();
				problem.setTitle(resultSet.getString("title"));
				problem.setDescription(resultSet.getString("description"));
				problem.setSampleInputProblem(resultSet.getString("sampleinputproblem"));
				problem.setSampleOutputProblem(resultSet.getString("sampleoutputproblem"));
				problem.setTimelimit(resultSet.getDouble("timelimit"));
				problem.setContestId(resultSet.getInt("contestId"));
				problem.setProblemId(resultSet.getInt("problemId"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return problem;
  }

  @Override
  public List<Problem> listProblems() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Problem> problems = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.problem as p order by p.problemId");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Problem problem = new Problem();
        problem.setProblemId(resultSet.getInt("problemId"));
				problem.setTitle(resultSet.getString("title"));
				problem.setDescription(resultSet.getString("description"));
				problem.setSampleInputProblem(resultSet.getString("sampleinputproblem"));
				problem.setSampleOutputProblem(resultSet.getString("sampleoutputproblem"));
				problem.setTimelimit(resultSet.getDouble("timelimit"));
				problem.setContestId(resultSet.getInt("contestId"));

				problems.add(problem);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return problems;
  }
  
}
