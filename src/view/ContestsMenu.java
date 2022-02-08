package src.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import src.model.bean.Contest;
import src.model.bean.ContestStatus;
import src.model.dao.ContestDAO;

import static src.view.App.input;
import static src.view.App.printHeader;
import static src.view.App.waitEnter;
import static src.view.App.clearBuffer;
import static src.view.App.clearConsole;
import static src.view.App.formatId;

public class ContestsMenu {
  private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
  private static GregorianCalendar gregorianCalendar = new GregorianCalendar();
  private static int optionMenu = 0;

  public static void contestMenu() throws IOException {
    do {
      printHeader();

      System.out.println("\033[0;34m1) Criar contest\033[0m\n" +
          "\033[0;34m2) Listar contests\033[0m\n" +
          "\033[0;34m3) Iniciar contest\033[0m\n" +
          "\033[0;34m4) Finalizar contest\033[0m\n" +
          "\033[0;34m5) Retornar\033[0m\n");

      optionMenu = input.nextInt();
      clearBuffer();

      try {
        switch (optionMenu) {
          case 1:
            createContest();
            break;
          case 2:
            listContests();
            break;
          case 3:
            startContest();
            break;
          case 4:
            finishContest();
            break;
          case 5:
            break;
          default:
            System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
            waitEnter();
            clearConsole();
        }
      } catch (IOException e) {
        System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
        waitEnter();
        clearConsole();
      }

    } while (optionMenu != 5);
  }

  public static void createContest() throws IOException {
    Contest contest = new Contest();
    contest.setStatus(ContestStatus.NOT_STARTED);

    // Lendo titulo do contest
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o titulo do contest:\n> ");
        contest.setTitle(input.nextLine());
        if (contest.getTitle().isEmpty()) {
          System.out.println("O titulo do contest deve ter no mínimo 1 caractere!" +
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

    // Lendo a data do contest
    while (true) {
      try {
        printHeader();
        System.out.print("Digite a data do contest no formato (dd/MM/yyyy hh:mm):\n> ");
        gregorianCalendar.setTime(formatter.parse(input.nextLine()));
        contest.setDateTime(new java.sql.Date(gregorianCalendar.getTime().getTime()));
        clearConsole();
        break;
      } catch (ParseException e) {
        System.out.println("Formato inválido\n Pressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo a duração do contest
    while (true) {
      try {
        printHeader();
        System.out.print("Digite a duração do contest:\n> ");
        contest.setDuration(input.nextDouble());
        clearConsole();
        break;
      } catch (Exception e) {
        System.out.println("Formato inválido\n Pressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo o local do contest
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o local do contest:\n> ");
        contest.setPlace(input.nextLine());
        if (contest.getPlace().isEmpty()) {
          System.out.println("Digite algum local!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
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

    // Lendo o juiz do contest
    while (true) {
      try {
        printHeader();
        // listJudges();
        System.out.print("Digite o índice do juiz:\n> ");
        contest.setJudgeId(input.nextInt());
        // check indice do juiz
        clearConsole();
        break;
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Índice inválido\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    ContestDAO contestDAO = new ContestDAO();
    if (contestDAO.save(contest)) {
      System.out.println("O contest foi criado com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao criar contest!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void listContests() throws IOException {
    printHeader();

    ContestDAO contestDAO = new ContestDAO();
    List<Contest> contests = contestDAO.listContests();

    System.out.println("Listando contests cadastrados:");
    for (Contest contest : contests) {
      System.out
          .println("- " + formatId(contest.getContestId()) + " | " + contest.getStatus() + " | " + contest.getTitle());
    }

    if (contests.size() == 0) {
      System.out.println("Nenhum contest cadastrado!");
    }

    System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

    waitEnter();
    clearConsole();
  }

  public static void startContest() throws IOException {
    ContestDAO contestDAO = new ContestDAO();
    Contest contest = null;

    // Lendo contest
    while (true) {
      try {
        printHeader();

        List<Contest> contests = contestDAO.listContests();

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
        clearConsole();
        break;
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Contest não existe\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    if (!contest.getStatus().equals(ContestStatus.NOT_STARTED)) {
      System.out.println("Não é possível iniciar o contest!\nPressione \033[1;32mENTER\033[0m para voltar.");
      waitEnter();
      clearConsole();
      return;
    }

    contest.setStatus(ContestStatus.RUNNING);

    if (contestDAO.update(contest.getContestId(), contest)) {
      System.out.println("O contest foi iniciado com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao iniciar contest!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void finishContest() throws IOException {
    ContestDAO contestDAO = new ContestDAO();
    Contest contest = null;

    // Lendo contest
    while (true) {
      try {
        printHeader();

        List<Contest> contests = contestDAO.listContests();

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
        clearConsole();
        break;
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Contest não existe\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    if (!contest.getStatus().equals(ContestStatus.RUNNING)) {
      System.out.println("O contest precisa estar em andamento!\nPressione \033[1;32mENTER\033[0m para voltar.");
      waitEnter();
      clearConsole();
      return;
    }

    contest.setStatus(ContestStatus.FINISHED);

    if (contestDAO.update(contest.getContestId(), contest)) {
      System.out.println("O contest foi finalizado com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao finalizar contest!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

}
