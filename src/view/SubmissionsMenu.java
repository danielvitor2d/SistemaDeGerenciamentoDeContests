package src.view;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import src.model.bean.Problem;
import src.model.bean.Submission;
import src.model.bean.SubmissionStatus;
import src.model.bean.Team;
import src.model.dao.ProblemDAO;
import src.model.dao.SubmissionsDAO;
import src.model.dao.TeamDAO;

import static src.view.App.input;
import static src.view.App.printHeader;
import static src.view.App.waitEnter;
import static src.view.App.clearBuffer;
import static src.view.App.clearConsole;
import static src.view.App.formatId;

public class SubmissionsMenu {
  private static int optionMenu = 0;

  public static void submissionsMenu() throws IOException {
    do {
      printHeader();

      System.out.println("\033[0;34m1) Cadastrar submissão\033[0m\n" +
          "\033[0;34m2) Listar submissões\033[0m\n" +
          "\033[0;34m3) Listar submissões por equipe\033[0m\n" +
          "\033[0;34m4) Retornar\033[0m\n");

      optionMenu = input.nextInt();
      clearBuffer();

      try {
        switch (optionMenu) {
          case 1:
            createSubmission();
            break;
          case 2:
            listSubmissions();
            break;
          case 3:
            listSubmissionsByTeam();
            break;
          case 4:
            break;
          default:
            System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
            waitEnter();
            clearConsole();
            continue;
        }
      } catch (IOException e) {
        System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
        waitEnter();
        clearConsole();
      }

    } while (optionMenu != 4);
  }

  public static void createSubmission() throws IOException {
    Submission submission = new Submission();

    submission.setTimestamp(new Timestamp(System.currentTimeMillis()));

    // Lendo status da submissão
    while (true) {
      try {
        printHeader();
        System.out.print("Insira o status da submissão (AC - Aceita, WA - Errada, RE - Erro em tempo de Execução, MLE - Memória limite excedida, TLE - Tempo limite excedido):\n> ");
        String opcao = input.nextLine();
        if (opcao.isEmpty()) {
          System.out.println("Erro! Pressione \033[1;32mENTER\033[0m para tentar de novo.");
          clearConsole();
          continue;
        }
        switch (opcao) {
          case "AC":
            submission.setStatus(SubmissionStatus.AC);
            break;
          case "WA":
            submission.setStatus(SubmissionStatus.WA);
            break;
          case "RE":
            submission.setStatus(SubmissionStatus.RE);
            break;
          case "MLE":
            submission.setStatus(SubmissionStatus.MLE);
            break;
          case "TLE":
            submission.setStatus(SubmissionStatus.TLE);
            break;
          default:
            System.out.println("Opção inválida! Pressione \033[1;32mENTER\033[0m para tentar de novo.");
            waitEnter();
            clearConsole();
            continue;
        }
        clearConsole();
        break;
      }
      catch (Exception e) {
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo o código fonte da submissão
    while (true) {
      try {
        printHeader();
        System.out.print("Insira o endereço do código fonte:\n> ");
        submission.setSourceCode(input.nextLine());
        if (submission.getSourceCode().isEmpty()) {
          System.out.println("Entrada inválida\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Listando problemas
    ProblemDAO problemDAO = new ProblemDAO();
    Problem problem = null;

    List<Problem> problems = problemDAO.listProblems();

    while(true) {
      try {
        printHeader();
    
        System.out.println("Listando problemas cadastrados:");
        for (Problem _problem : problems) {
          System.out
              .println("- " + formatId(_problem.getProblemId()) + " | " + _problem.getTitle() + " | " + _problem.getDescription());
        }
        
        if (problems.size() == 0) {
          System.out.println("Nenhum problema cadastrado!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Insira o ID do problema:\n> ");
        int ID = input.nextInt();
        clearBuffer();
        problem = problemDAO.getById(ID);
        if (problem == null) {
          clearConsole();
          continue;
        }
        clearConsole();
        break;
      }
      catch (Exception e) {
        System.out.println(e.getMessage() + " Pressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    submission.setProblemId(problem.getProblemId());

    // Listando times
    TeamDAO teamDAO = new TeamDAO();
    Team team = null;

    List<Team> teams = teamDAO.listTeams();

    while(true) {
      try {
        printHeader();
    
        System.out.println("Listando times cadastrados:");
        for (Team _team : teams) {
          System.out
              .println("- " + formatId(_team.getTeamId()) + " | " + _team.getTeamName());
        }
        
        if (teams.size() == 0) {
          System.out.println("Nenhum time cadastrado!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Insira o ID do time:\n> ");
        int ID = input.nextInt();
        clearBuffer();
        team = teamDAO.getById(ID);
        if (team == null) {
          clearConsole();
          printHeader();
          throw new Exception("ID incorreto\n");
        }
        clearConsole();
        break;
      }
      catch (Exception e) {
        System.out.println(e.getMessage() + " Pressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
      }
    }

    submission.setTeamId(team.getTeamId());

    printHeader();

    SubmissionsDAO submissionsDAO = new SubmissionsDAO();
    if (submissionsDAO.save(submission)) {
      System.out.println("Submissão cadastrada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao cadastrar submissão!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void listSubmissions() throws IOException {
    printHeader();

    SubmissionsDAO submissionsDAO = new SubmissionsDAO();
    List<Submission> submissions = submissionsDAO.listSubmissions();

    System.out.println("Listando submissões cadastradas:");
    System.out.println("ID da Submissão  | Status da Submissão | ID do Problema | ID do Time");
    for (Submission submission : submissions) {
      System.out.println(formatId(submission.getSubmissionId()) + " | " + submission.getStatus() + " | " + submission.getProblemId() + " | " + submission.getTeamId());
    }
    
    if (submissions.size() == 0) {
      System.out.println("Nenhuma submissão cadastrada!");
    }

    System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

    waitEnter();
    clearConsole();
  }

  public static void listSubmissionsByTeam() throws IOException {
    TeamDAO teamDAO = new TeamDAO();
    int ID = 0;
    Team team = null;

    List<Team> teams = teamDAO.listTeams();

    while(true) {
      try {
        printHeader();
    
        System.out.println("Listando times cadastrados:");
        for (Team _team : teams) {
          System.out
              .println("- " + formatId(_team.getTeamId()) + " | " + _team.getTeamName());
        }
        
        if (teams.size() == 0) {
          System.out.println("Nenhum time cadastrado!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Insira o ID do time:\n> ");
        ID = input.nextInt();
        clearBuffer();
        team = teamDAO.getById(ID);
        if (team == null) {
          clearConsole();
          printHeader();
          throw new Exception("ID incorreto\n");
        }
        clearConsole();
        break;
      }
      catch (Exception e) {
        System.out.println(e.getMessage() + " Pressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
      }
    }

    printHeader();

    SubmissionsDAO submissionsDAO = new SubmissionsDAO();
    List<Submission> submissions = submissionsDAO.listSubmissionsByTeam(team.getTeamId());

    System.out.println("Listando submissões cadastradas:");
    System.out.println("ID da Submissão  | Status da Submissão | ID do Problema | ID do Time");
    for (Submission submission : submissions) {
      System.out.println(formatId(submission.getSubmissionId()) + " | " + submission.getStatus() + " | " + submission.getProblemId() + " | " + submission.getTeamId());
    }
    
    if (submissions.size() == 0) {
      System.out.println("Nenhuma submissão cadastrada!");
    }

    System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

    waitEnter();
    clearConsole();
  }
}
