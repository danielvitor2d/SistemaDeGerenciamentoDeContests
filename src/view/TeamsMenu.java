package view;

import static view.App.input;
import static view.App.printHeader;
import static view.App.waitEnter;

import java.io.IOException;
import java.util.List;

import model.bean.TeamAndContestRegistered;
import model.bean.TeamAndCountOfSubmissions;
import model.bean.ViewTeam;
import model.dao.TeamDAO;

import static view.App.clearBuffer;
import static view.App.clearConsole;

public class TeamsMenu {
  public static void teamsMenu() {
    int optionMenu = 0;
    do {
      printHeader();
      
      System.out.println("\033[0;34m1) Listar times com nomes dos membros\033[0m\n" +
                         "\033[0;34m2) Listar times e somatório da quantidade de problemas nos contests\033[0m\n" +
                         "\033[0;34m3) Listar times e quantidade total de submissões\033[0m\n" +
                         "\033[0;34m4) Retornar\033[0m\n");
      
      optionMenu = input.nextInt();
      clearBuffer();
      
      try {
        switch(optionMenu) {
          case 1:
            listarTimeComNomesDosMembros();
            break;
          case 2:
            listarTimeQuantidadeDeProblemasEmTodosOsContests();
            break;
          case 3:
            listarTimeEQuantidadeDeSubmissoes();
            break;
          case 4:

            break;
          case 5:
  
            break;
          default:
            System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
            waitEnter();
        }
      } catch (IOException e) {
        System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
      }
      
    } while (optionMenu != 4);
  }

  public static void listarTimeComNomesDosMembros() throws IOException {
    printHeader();

    TeamDAO teamDAO = new TeamDAO();

    List<ViewTeam> viewTeams = teamDAO.listarTimeComNomesDosMembros();

    System.out.println("Aluno 01 | Aluno 02 | Aluno 03");
    for (ViewTeam viewTeam : viewTeams) {
      System.out.println(viewTeam.getStudent01() + " | " + viewTeam.getStudent02() + " | " + viewTeam.getStudent03() + " | " + viewTeam.getCoach());
    }

    System.out.println("Aperte \033[1;32mENTER\033[0m para voltar");
    waitEnter();
    clearConsole();
  }

  public static void listarTimeQuantidadeDeProblemasEmTodosOsContests() throws IOException {
    printHeader();

    TeamDAO teamDAO = new TeamDAO();

    List<TeamAndContestRegistered> teams = teamDAO.listarTimeQuantidadeDeProblemasEmTodosOsContests();

    System.out.println("Nome do Time | Somatório da quantidade de problemas em todos os contests");
    for (TeamAndContestRegistered team : teams) {
      System.out.println(team.getTeamName() + " | " + team.getSum());
    }

    System.out.println("Aperte \033[1;32mENTER\033[0m para voltar");
    waitEnter();
    clearConsole();
  }

  public static void listarTimeEQuantidadeDeSubmissoes() throws IOException {
    printHeader();

    TeamDAO teamDAO = new TeamDAO();

    List<TeamAndCountOfSubmissions> teams = teamDAO.listarTimeEQuantidadeDeSubmissoes();

    System.out.println("Nome do Time | Quantidade total de submissões");
    for (TeamAndCountOfSubmissions team : teams) {
      System.out.println(team.getTeamname() + " | " + team.getCount());
    }

    System.out.println("Aperte \033[1;32mENTER\033[0m para voltar");
    waitEnter();
    clearConsole();
  }


}
