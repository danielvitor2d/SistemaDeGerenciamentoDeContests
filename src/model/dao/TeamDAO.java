package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
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
    
}
