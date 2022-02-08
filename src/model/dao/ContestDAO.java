package src.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.connection.ConnectionFactory;
import src.model.bean.Contest;
import src.model.bean.ContestStatus;

public class ContestDAO implements IContestDAO {

  @Override
  public boolean save(Contest contest) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("insert into manage_contests.contest (status, title, date, duration, place, judgeid) values  (?::manage_contests.contestStatus, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, contest.getStatus().toString());
			preparedStatement.setString(2, contest.getTitle());
			preparedStatement.setDate(3, contest.getDateTime());
			preparedStatement.setDouble(4, contest.getDuration());
			preparedStatement.setString(5, contest.getPlace());
			preparedStatement.setInt(6, contest.getJudgeId());

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
  public boolean update(int contestId, Contest contest) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("update manage_contests.contest set status = ?::manage_contests.contestStatus, title = ?, date = ?, duration = ?, place = ?, judgeid = ? WHERE contestId = ?");
			preparedStatement.setString(1, contest.getStatus().toString());
			preparedStatement.setString(2, contest.getTitle());
			preparedStatement.setDate(3, contest.getDateTime());
			preparedStatement.setDouble(4, contest.getDuration());
			preparedStatement.setString(5, contest.getPlace());
			preparedStatement.setInt(6, contest.getJudgeId());
      preparedStatement.setInt(7, contest.getContestId());

      result = (preparedStatement.executeUpdate() == 1);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}

		return result;
  }

  @Override
  public boolean delete(int contestId) {
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;

    boolean result = false;

    try {
      stmt = con.prepareStatement("delete from manage_contests.contest where contestId = ?");
      stmt.setInt(1, contestId);

      result = (stmt.executeUpdate() == 1);

    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    } finally {
      ConnectionFactory.closeConnection(con, stmt);
    }

    return result;
  }

  @Override
  public Contest getById(int contestId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Contest contest = null;

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.contest as c where c.contestId = (?) order by c.contestId");
			preparedStatement.setInt(1, contestId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				contest = new Contest();
				contest.setContestId(resultSet.getInt("contestId"));
				contest.setTitle(resultSet.getString("title"));
				contest.setPlace(resultSet.getString("place"));
				contest.setDuration(resultSet.getDouble("duration"));
				contest.setDateTime(resultSet.getDate("date"));
				contest.setJudgeId(resultSet.getInt("judgeId"));
				contest.setStatus(ContestStatus.valueOf(resultSet.getString("status")));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return contest;
  }

  @Override
  public List<Contest> listContests() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Contest> contests = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.contest as c order by c.contestId");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Contest contest = new Contest();

        contest.setContestId(resultSet.getInt("contestId"));
        contest.setStatus(ContestStatus.valueOf(resultSet.getString("status")));
        contest.setDuration(resultSet.getDouble("duration"));
        contest.setTitle(resultSet.getString("title"));
        contest.setJudgeId(resultSet.getInt("judgeId"));
        contest.setPlace(resultSet.getString("place"));
        contest.setDateTime(resultSet.getDate("date"));

				contests.add(contest);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return contests;
  }
  
}
