package model.dao;

import java.util.List;

import model.bean.TeamAndContestRegistered;
import model.bean.TeamAndCountOfSubmissions;
import model.bean.ViewTeam;

interface ITeamDAO {
  List<ViewTeam> listarTimeComNomesDosMembros();
  List<TeamAndContestRegistered> listarTimeQuantidadeDeProblemasEmTodosOsContests();
  List<TeamAndCountOfSubmissions> listarTimeEQuantidadeDeSubmissoes();
}
