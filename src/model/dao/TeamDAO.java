package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.bean.Team;
import model.bean.TeamAndContestRegistered;
import model.bean.TeamAndCountOfSubmissions;
import model.bean.ViewTeam;

public class TeamDAO implements ITeamDAO {

  @Override
  public List<ViewTeam> listarTimeComNomesDosMembros() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<ViewTeam> viewTeams = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.view_teams");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ViewTeam viewTeam = new ViewTeam();

				viewTeam.setStudent01(resultSet.getString("student01"));
				viewTeam.setStudent02(resultSet.getString("student02"));
        viewTeam.setStudent03(resultSet.getString("student03"));
        viewTeam.setCoach(resultSet.getString("coach"));

				viewTeams.add(viewTeam);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar!\n" + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

    return viewTeams;
  }

	@Override
	public List<TeamAndContestRegistered> listarTimeQuantidadeDeProblemasEmTodosOsContests() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<TeamAndContestRegistered> viewTeams = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select team_contest_count_problems.teamName, sum(team_contest_count_problems.count_of_problems) from ((manage_contests.contest_registered_team as crt natural join manage_contests.team as t) as team_contest natural join manage_contests.view_problems_of_contest as vpc) as team_contest_count_problems group by (team_contest_count_problems.teamName)");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				TeamAndContestRegistered viewTeam = new TeamAndContestRegistered();

				viewTeam.setTeamName(resultSet.getString("teamname"));
				viewTeam.setSum(resultSet.getInt("sum"));

				viewTeams.add(viewTeam);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar!\n" + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

    return viewTeams;
	}

	@Override
	public List<TeamAndCountOfSubmissions> listarTimeEQuantidadeDeSubmissoes() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<TeamAndCountOfSubmissions> teams = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select res.teamName, count(res.submissionId) from (manage_contests.team as t natural left join manage_contests.submission as s) as res group by (res.teamName)");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				TeamAndCountOfSubmissions team = new TeamAndCountOfSubmissions();

				team.setTeamname(resultSet.getString("teamname"));
				team.setCount(resultSet.getInt("count"));

				teams.add(team);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar!\n" + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

    return teams;
	}

	@Override
	public boolean save(Team team) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values  (?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, team.getTeamName());
			preparedStatement.setString(2, team.getTeamPhotoUrl());
			preparedStatement.setInt(3, team.getStudentId01());
			preparedStatement.setInt(4, team.getStudentId02());
			preparedStatement.setInt(5, team.getStudentId03());
			preparedStatement.setInt(6, team.getCoachId());

			result = preparedStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}

		return result;
	}

	@Override
	public boolean update(int teamId, Team team) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		boolean result = false;

		try {
			preparedStatement = connection.prepareStatement("update manage_contests.team set teamName = ?, teamPhotoUrl = ?, studentId01 = ?, studentId02 = ?, studentId03 = ?, coachId = ? WHERE teamId = ?");
			preparedStatement.setString(1, team.getTeamName());
			preparedStatement.setString(2, team.getTeamPhotoUrl());
			preparedStatement.setInt(3, team.getStudentId01());
			preparedStatement.setInt(4, team.getStudentId02());
			preparedStatement.setInt(5, team.getStudentId03());
			preparedStatement.setInt(6, team.getCoachId());
			preparedStatement.setInt(7, team.getTeamId());

			result = (preparedStatement.executeUpdate() == 1);

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public boolean delete(int teamId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		boolean result = false;

		try {
			stmt = con.prepareStatement("delete from manage_contests.team as t where T.teamId = (?)");
			stmt.setInt(1, teamId);

			result = (stmt.executeUpdate() == 1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		return result;
	}

	@Override
	public Team getById(int teamId) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Team team = null;

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.team as t where t.teamId = (?)");
			preparedStatement.setInt(1, teamId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				team = new Team();
				team.setTeamId(resultSet.getInt("teamId"));
				team.setTeamName(resultSet.getString("teamName"));
				team.setStudentId01(resultSet.getInt("studentId01"));
				team.setStudentId02(resultSet.getInt("studentId02"));
				team.setStudentId03(resultSet.getInt("studentId03"));
				team.setCoachId(resultSet.getInt("coachId"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return team;
	}

	@Override
	public List<Team> listTeams() {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Team> teams = new ArrayList<>();

		try {
			preparedStatement = connection.prepareStatement("select * from manage_contests.team as t order by t.teamId");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Team team = new Team();

				team.setTeamId(resultSet.getInt("teamId"));
				team.setTeamName(resultSet.getString("teamName"));
				team.setStudentId01(resultSet.getInt("studentId01"));
				team.setStudentId02(resultSet.getInt("studentId02"));
				team.setStudentId03(resultSet.getInt("studentId03"));
				team.setCoachId(resultSet.getInt("coachId"));

				teams.add(team);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(connection, preparedStatement, resultSet);
		}

		return teams;
	}
    
}
