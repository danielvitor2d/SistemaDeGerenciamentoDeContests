package src.view;

import java.io.IOException;
import java.util.List;

import src.model.bean.Contest;
import src.model.bean.ContestStatus;
import src.model.bean.Problem;
import src.model.dao.ContestDAO;
import src.model.dao.ProblemDAO;

import static src.view.App.input;
import static src.view.App.printHeader;
import static src.view.App.waitEnter;
import static src.view.App.clearBuffer;
import static src.view.App.clearConsole;
import static src.view.App.formatId;

public class ProblemsMenu {
  private static int optionMenu = 0;

  public static void problemsMenu() throws IOException {
    do {
      printHeader();

      System.out.println("\033[0;34m1) Cadastrar problema\033[0m\n" +
          "\033[0;34m2) Listar problemas\033[0m\n" +
          "\033[0;34m3) Listar problema específico\033[0m\n" +
          "\033[0;34m4) Retornar\033[0m\n");

      optionMenu = input.nextInt();
      clearBuffer();

      try {
        switch (optionMenu) {
          case 1:
            createProblem();
            break;
          case 2:
            listProblems();
            break;
          case 3:
            getProblem();
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

  public static void createProblem() throws IOException {
    Problem problem = new Problem();

    // Lendo o titulo do problema
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o titulo do problema:\n> ");
        problem.setTitle(input.nextLine());
        if (problem.getTitle().isEmpty()) {
          System.out.println("O nome do problema deve ter no mínimo 1 caractere!" +
              "\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }
  
    // Lendo a descrição do problema
    while (true) {
      try {
        printHeader();
        System.out.print("Digite a descrição do problema:\n> ");
        problem.setDescription(input.nextLine());
        if (problem.getDescription().isEmpty()) {
          System.out.println("A descrição do problema deve ter no mínimo 1 caractere!" +
              "\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo o time limit do problema
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o tempo limite do problema (exemplo: 1.5):\n> ");
        problem.setTimelimit(input.nextDouble());
        clearBuffer();
        if (problem.getTimelimit() <= 0.0) {
          System.out.println("Inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("Inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }
  
    // Lendo exemplo de entrada
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o endereço do exemplo de entrada do problema:\n> ");
        problem.setSampleInputProblem(input.nextLine());
        if (problem.getSampleInputProblem().isEmpty()) {
          System.out.println("Inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("Inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo exemplo de saída
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o endereço do exemplo de saída do problema:\n> ");
        problem.setSampleOutputProblem(input.nextLine());
        if (problem.getSampleOutputProblem().isEmpty()) {
          System.out.println("Inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("Inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    printHeader();

    ContestDAO contestDAO = new ContestDAO();
    List<Contest> contests = contestDAO.listContests();
    Contest contest = null;

    // Lendo contest
    while (true) {
      try {
        printHeader();

        System.out.println("Listando contests cadastrados:");
        for (Contest _contest : contests) {
          System.out.println(
              "- " + formatId(_contest.getContestId()) + " | " + _contest.getStatus() + " | " + _contest.getTitle());
        }

        if (contests.size() == 0) {
          System.out.println("Nenhum contest cadastrado!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o ID do contest:\n> ");
        int contestId = input.nextInt();
        contest = contestDAO.getById(contestId);
        if (contest == null) {
          throw new IndexOutOfBoundsException();
        }

        if (!contest.getStatus().equals(ContestStatus.NOT_STARTED)) {
          System.out.println("Não é possível cadastrar problemas no contest depois de iniciado!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          continue;
        }

        clearConsole();
        break;
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Contest não existe\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    problem.setContestId(contest.getContestId());

    ProblemDAO problemDAO = new ProblemDAO();

    if (problemDAO.save(problem)) {
      System.out.println("O problema foi cadastrado com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao cadastrar problema!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void listProblems() throws IOException {
    printHeader();

    ProblemDAO problemDAO = new ProblemDAO();
    List<Problem> problems = problemDAO.listProblems();

    System.out.println("Listando contests cadastrados:");
    System.out.println("ID do Problema   |   Título do Problema    |   Descrição do Problema ");
    for (Problem problem : problems) {
      System.out
          .println("- " + formatId(problem.getProblemId()) + " | " + problem.getTitle() + " | " + problem.getDescription());
    }

    if (problems.size() == 0) {
      System.out.println("Nenhum problema cadastrado!");
    }

    System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

    waitEnter();
    clearConsole();
  }

  public static void getProblem() throws IOException {
    Problem problem = null;
    ProblemDAO problemDAO = new ProblemDAO();
    List<Problem> problems = problemDAO.listProblems();

    while (true) {
      try {
        printHeader();

        System.out.println("Listando contests cadastrados:");
        System.out.println("ID do Problema   |   Título do Problema    |   Descrição do Problema    |    ID do contest  :");
        for (Problem _problem : problems) {
          System.out
              .println("- " + formatId(_problem.getProblemId()) + " | " + _problem.getTitle() + " | " + _problem.getDescription() + " | " + _problem.getContestId());
        }

        if (problems.size() == 0) {
          System.out.println("Nenhum problema cadastrado!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o ID do problema:\n> ");
        int problemId = input.nextInt();
        problem = problemDAO.getById(problemId);
        if (problem == null) {
          throw new IndexOutOfBoundsException();
        }
        clearConsole();
        break;

      } catch (IndexOutOfBoundsException e) {
        System.out.println("Problem não existe\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    printHeader();

    System.out.println("ID do problema: " + problem.getProblemId());
    System.out.println("ID do contest: " + problem.getContestId());
    System.out.println("Título: " + problem.getTitle());
    System.out.println("Descrição: " + problem.getDescription());
    System.out.println("Endereço do exemplo de entrada: " + problem.getSampleInputProblem());
    System.out.println("Endereço do exemplo de saída: " + problem.getSampleOutputProblem());
    System.out.println("Tempo limite: " + problem.getTimelimit());

    System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

    waitEnter();
    clearConsole();
  }
}
