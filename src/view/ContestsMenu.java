package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.bean.Contest;
import model.bean.ContestStatus;
import model.dao.ContestDAO;

import static view.App.input;
import static view.App.printHeader;
import static view.App.waitEnter;
import static view.App.clearBuffer;
import static view.App.clearConsole;

public class ContestsMenu {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    private static GregorianCalendar gregorianCalendar = new GregorianCalendar();

    public static void contestMenu() {
        int optionMenu = 0;
        do {
            printHeader();

            System.out.println("\033[0;34m1) Criar contest\033[0m\n" +
                    "\033[0;34m2) Listar contests\033[0m\n" +
                    "\033[0;34m3) Iniciar contest\033[0m\n" +
                    "\033[0;34m4) Cancelar contest\033[0m\n" +
                    "\033[0;34m5) Remover contest\033[0m\n" +
                    "\033[0;34m6) Retornar\033[0m\n");

            optionMenu = input.nextInt();
            clearBuffer();

            try {
                switch(optionMenu) {
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
                        cancelContest();
                        break;
                    case 5:
                        removeContest();
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
                        waitEnter();
                }
            } catch (IOException e) {
                System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
            }

        } while (optionMenu != 6);
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
                contest.setDateTime(gregorianCalendar.getTime());
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

        // ContestDAO contestDAO = new ContestDAO();
        // if (contestDAO.save(contest)) {
        //     System.out.println("O contest foi criado com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
        // } else {
        //     System.out.println("Erro ao criar contest!\nPressione \033[1;32mENTER\033[0m para voltar.");
        // }

        waitEnter();
        clearConsole();
    }

    public static void listContests() throws IOException {
        printHeader();

        // ContestDAO contestDAO = new ContestDAO();
        // List<Contest> contests = contestDAO.listContests();
        
        // System.out.println("Listando contests cadastrados:");
        // for (Contest contest : contests) {
        //   System.out.println("- " + formatId(contest.getContestId()) + " | " + contest.getStatus() + " | " + contest.getTitle());
        // }
    
        // System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");
    
        waitEnter();
        clearConsole();
    }

    public static void startContest() throws IOException {
        printHeader();

        Contest contest = new Contest();

        while (true) {
            try {
                printHeader();
                // listContests
                System.out.print("Digite o ID do contest:\n> ");
                contest.setContestId(input.nextInt());
                // check ID here
                clearConsole();
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Índice inválido\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
                waitEnter();
                clearConsole();
            }
        }

        ContestDAO contestDAO = new ContestDAO();

        // if (contestDAO.delete(c.getPersonId())) {
        //     System.out.println("A pessoa foi deletada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
        // } else {
        //     System.out.println("Erro ao deletar pessoa!\nPressione \033[1;32mENTER\033[0m para voltar.");
        // }

        waitEnter();
        clearConsole();
    }

    public static void cancelContest() throws IOException {
        
    }

    public static void removeContest() throws IOException {

    }

    private static String formatId(int id) {
        if (id > 99) return String.valueOf(id);
        if (id > 9) return "0"+String.valueOf(id);
        return "00"+String.valueOf(id);
    }
}
