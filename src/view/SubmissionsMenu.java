package view;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.bean.Contest;
import model.bean.ContestStatus;
import model.bean.Submission;
import model.bean.SubmissionStatus;
import model.dao.ContestDAO;
import model.dao.SubmissionsDAO;

import static view.App.input;
import static view.App.printHeader;
import static view.App.waitEnter;
import static view.App.clearBuffer;
import static view.App.clearConsole;
import static view.App.formatId;

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

  }

  public static void listSubmissionsByTeam() throws IOException {
    
  }
}
