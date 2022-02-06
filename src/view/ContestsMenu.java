package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import model.bean.Contest;
import model.bean.ContestStatus;

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
                    System.out.println("Titulo inválido! O titulo do contest deve ter no mínimo 1 caractere!" +
                            "\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
                    waitEnter();
                    clearConsole();
                    continue;
                }
                clearConsole();
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n Pressione \033[1;32mENTER\033[0m para tentar de novo.");
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

        // Lendo email da pessoa
        while (true) {
            try {
                printHeader();
                System.out.print("Digite o e-mail da pessoa:\n> ");
                person.setEmail(input.nextLine());
                if (person.getEmail().isEmpty()) {
                    System.out.println("E-mail inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
                    waitEnter();
                    clearConsole();
                    continue;
                }
                clearConsole();
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n Pressione \033[1;32mENTER\033[0m para tentar de novo.");
                waitEnter();
                clearConsole();
            }
        }

        // Lendo telefone da pessoa
        while (true) {
            try {
                printHeader();
                System.out.print("Digite o telefone da pessoa:\n> ");
                person.setPhone(input.nextLine());
                if (person.getPhone().isEmpty()) {
                    System.out.println("Telefone inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
                    waitEnter();
                    clearConsole();
                    continue;
                }
                clearConsole();
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n Pressione \033[1;32mENTER\033[0m para tentar de novo.");
                waitEnter();
                clearConsole();
            }
        }

        // Lendo universidade da pessoa
        while (true) {
            try {
                printHeader();
                System.out.print("Digite a universidade da pessoa:\n> ");
                person.setUniversity(input.nextLine());
                if (person.getUniversity().isEmpty()) {
                    System.out.println("Universidade inválida!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
                    waitEnter();
                    clearConsole();
                    continue;
                }
                clearConsole();
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n Pressione \033[1;32mENTER\033[0m para tentar de novo.");
                waitEnter();
                clearConsole();
            }
        }

        // Lendo tipo da pessoa
        while (true) {
            try {
                printHeader();
                System.out.print("Insira o tipo da pessoa (A - Aluno, C - Coach, J - Juiz):\n> ");
                String letra = input.next().toUpperCase();
                clearBuffer();
                switch (letra) {
                    case "A":
                        person.setPersonType(PersonType.STUDENT);
                        break;
                    case "C":
                        person.setPersonType(PersonType.COACH);
                        break;
                    case "J":
                        person.setPersonType(PersonType.JUDGE);
                        break;
                    default:
                        System.out.println("Opção inválida! Pressione \033[1;32mENTER\033[0m para tentar de novo.");
                        waitEnter();
                        clearConsole();
                }
                clearConsole();
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage() + "\n Pressione \033[1;32mENTER\033[0m para tentar de novo.");
                waitEnter();
                clearConsole();
            }
        }

        PersonDAO personDAO = new PersonDAO();
        if (personDAO.save(person)) {
            System.out.println("A pessoa foi criada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
        } else {
            System.out.println("Erro ao criar pessoa!\nPressione \033[1;32mENTER\033[0m para voltar.");
        }

        waitEnter();
        clearConsole();
    }

    public static void listContests() throws IOException {
        // printHeader();

        // PersonDAO personDAO = new PersonDAO();
        // List<Person> people = personDAO.listPeople();

        // System.out.println("Listando pessoas cadastradas:");
        // for (Person person : people) {
        //     System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        // }

        // System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

        // waitEnter();
        // clearConsole();
    }

    public static void startContest() throws IOException {
        // PersonDAO personDAO = new PersonDAO();
        // int ID = 0;
        // Person person = null;

        // while(true) {
        //     try {
        //         printHeader();PersonDAO personDAO = new PersonDAO();
        // int ID = 0;
        // Person person = null;

        // while(true) {
        //     try {
        //         printHeader();
        //         System.out.print("Insira o ID da pessoa:\n> ");
        //         ID = input.nextInt();
        //         person = personDAO.getById(ID);
        //         if (person == null) {
        //             clearConsole();
        //             printHeader();
        //             throw new Exception("ID incorreto\n");
        //         }
        //         clearConsole();
        //         break;
        //     }
        //     catch (Exception e) {
        //         System.out.println(e.getMessage() + " Pressione \033[1;32mENTER\033[0m para tentar de novo.");
        //         waitEnter();
        //     }
        //     finally {
        //         clearBuffer();
        //     }
        // }

        // if (personDAO.delete(person.getPersonId())) {
        //     System.out.println("A pessoa foi deletada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
        // } else {
        //     System.out.println("Erro ao deletar pessoa!\nPressione \033[1;32mENTER\033[0m para voltar.");
        // }

        // waitEnter();
        // clearConsole();
        //             printHeader();
        //             throw new Exception("ID incorreto\n");
        //         }
        //         clearConsole();
        //         break;
        //     }
        //     catch (Exception e) {
        //         System.out.println(e.getMessage() + " Pressione \033[1;32mENTER\033[0m para tentar de novo.");
        //         waitEnter();
        //     }
        //     finally {
        //         clearBuffer();
        //     }
        // }

        // if (personDAO.delete(person.getPersonId())) {
        //     System.out.println("A pessoa foi deletada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
        // } else {
        //     System.out.println("Erro ao deletar pessoa!\nPressione \033[1;32mENTER\033[0m para voltar.");
        // }

        // waitEnter();
        // clearConsole();
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
