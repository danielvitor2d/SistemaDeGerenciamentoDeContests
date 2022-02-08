package src.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.connection.ConnectionFactory;
import src.model.bean.Submission;
import src.model.bean.SubmissionStatus;

public class SubmissionsDAO implements ISubmissionsDAO {

  @Override
  public boolean save(Submission submission) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("insert into manage_contests.submission (status, timestamp, sourceCode,	problemId, teamId) values  (?::manage_contests.submissionStatus, ?, ?, ?, ?)");
			preparedStatement.setString(1, submission.getStatus().toString());
			preparedStatement.setTimestamp(2, submission.getTimestamp());
			preparedStatement.setString(3, submission.getSourceCode());
			preparedStatement.setInt(4, submission.getProblemId());
			preparedStatement.setInt(5, submission.getTeamId());

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
  public boolean update(int submissionId, Submission submission) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("update manage_contests.submission set status = ?::manage_contests.submissionStatus, timestamp = ?, sourceCode = ?, problemId = ?, teamId = ? where submissionId = ?");
			preparedStatement.setString(1, submission.getStatus().toString());
			preparedStatement.setTimestamp(2, submission.getTimestamp());
			preparedStatement.setString(3, submission.getSourceCode());
			preparedStatement.setInt(4, submission.getProblemId());
			preparedStatement.setInt(5, submission.getTeamId());
			preparedStatement.setInt(6, submission.getSubmissionId());

			result = preparedStatement.executeUpdate() == 1;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
		return result;
  }

  @Override
  public boolean delete(int submissionId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		boolean result = false;

		try {
			stmt = con.prepareStatement("delete from manage_contests.submission as s where s.submissionId = (?)");
			stmt.setInt(1, submissionId);

			result = (stmt.executeUpdate() == 1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		return result;
  }

  @Override
  public Submission getById(int submissionId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Submission submission = null;

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.submission as s where s.submissionId = (?)");
			preparedStatement.setInt(1, submissionId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				submission = new Submission();
				submission.setSubmissionId(resultSet.getInt("submissionId"));
				submission.setStatus(SubmissionStatus.valueOf(resultSet.getString("status")));
				submission.setSourceCode(resultSet.getString("sourceCode"));
				submission.setTeamId(resultSet.getInt("teamId"));
				submission.setProblemId(resultSet.getInt("problemId"));
        submission.setTimestamp(resultSet.getTimestamp("timestamp"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return submission;
  }

  @Override
  public List<Submission> listSubmissions() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Submission> submissions = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.submission as s order by s.submissionId");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Submission submission = new Submission();

				submission.setSubmissionId(resultSet.getInt("submissionId"));
				submission.setStatus(SubmissionStatus.valueOf(resultSet.getString("status")));
				submission.setSourceCode(resultSet.getString("sourceCode"));
				submission.setTeamId(resultSet.getInt("teamId"));
				submission.setProblemId(resultSet.getInt("problemId"));
        submission.setTimestamp(resultSet.getTimestamp("timestamp"));

				submissions.add(submission);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return submissions;
  }
  
  @Override
  public List<Submission> listSubmissionsByTeam(int teamId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Submission> submissions = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.submission as s where s.teamId = (?) order by s.submissionId");
			preparedStatement.setInt(1, teamId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Submission submission = new Submission();

				submission.setSubmissionId(resultSet.getInt("submissionId"));
				submission.setStatus(SubmissionStatus.valueOf(resultSet.getString("status")));
				submission.setSourceCode(resultSet.getString("sourceCode"));
				submission.setTeamId(resultSet.getInt("teamId"));
				submission.setProblemId(resultSet.getInt("problemId"));
        submission.setTimestamp(resultSet.getTimestamp("timestamp"));

				submissions.add(submission);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return submissions;
  }

}
