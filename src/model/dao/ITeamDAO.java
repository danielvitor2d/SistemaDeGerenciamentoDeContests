package src.model.dao;

import java.util.List;

import src.model.bean.Team;
import src.model.bean.TeamAndContestRegistered;
import src.model.bean.TeamAndCountOfSubmissions;
import src.model.bean.ViewTeam;

interface ITeamDAO {
  List<ViewTeam> listarTimeComNomesDosMembros();
  List<TeamAndContestRegistered> listarTimeQuantidadeDeProblemasEmTodosOsContests();
  List<TeamAndCountOfSubmissions> listarTimeEQuantidadeDeSubmissoes();
	boolean save(Team team);
	boolean update(int teamId, Team team);
	boolean delete(int teamId);
	Team getById(int teamId);
	List<Team> listTeams();
}
