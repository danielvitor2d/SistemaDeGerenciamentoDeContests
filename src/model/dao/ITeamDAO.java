package model.dao;

import java.util.List;

import model.bean.Team;
import model.bean.TeamAndContestRegistered;
import model.bean.TeamAndCountOfSubmissions;
import model.bean.ViewTeam;

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
