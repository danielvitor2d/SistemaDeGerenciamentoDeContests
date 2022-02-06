package view;

public class ContestsMenu {
    public static void contestMenu() {
        int optionMenu = 0;
        do {
            printHeader();

            System.out.println("\033[0;34m1) Adicionar contest\033[0m\n" +
                    "\033[0;34m2) Listar contest\033[0m\n" +
                    "\033[0;34m3) Atualizar contest\033[0m\n" +
                    "\033[0;34m4) Remover contest\033[0m\n" +
                    "\033[0;34m5) Retornar\033[0m\n");

            optionMenu = input.nextInt();
            clearBuffer();

            try {
                switch(optionMenu) {
                    case 1:
                        addContest();
                        break;
                    case 2:
                        listContest();
                        break;
                    case 3:
                        printHeader();
                        System.out.println("Menu de atualizar contest!");
                        System.out.println("Aperte \033[1;32mENTER\033[0m para voltar.");
                        waitEnter();
                        break;
                    case 4:
                        removeContest();
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

        } while (optionMenu != 5);
    }

    public static void addContest() throws IOException {
        Contest contest = new Contest();

        // Lendo titulo do contest
        while (true) {
            try {
                printHeader();
                System.out.print("Digite o titulo do contest:\n> ");
                contest.setName(input.nextLine());
                if (person.getName().isEmpty()) {
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

        // Lendo status do contest
        while (true) {
            try {
                printHeader();
                System.out.print("Digite a idade da pessoa:\n> ");
                person.setAge(input.nextInt());
                clearBuffer();
                if (person.getAge() <= 0) {
                    System.out.println("Idade inválida!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
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

    public static void listPeople() throws IOException {
        printHeader();

        PersonDAO personDAO = new PersonDAO();
        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
            System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

        waitEnter();
        clearConsole();
    }

    public static void removePerson() throws IOException {
        PersonDAO personDAO = new PersonDAO();
        int ID = 0;
        Person person = null;

        while(true) {
            try {
                printHeader();
                System.out.print("Insira o ID da pessoa:\n> ");
                ID = input.nextInt();
                person = personDAO.getById(ID);
                if (person == null) {
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
            finally {
                clearBuffer();
            }
        }

        if (personDAO.delete(person.getPersonId())) {
            System.out.println("A pessoa foi deletada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
        } else {
            System.out.println("Erro ao deletar pessoa!\nPressione \033[1;32mENTER\033[0m para voltar.");
        }

        waitEnter();
        clearConsole();
    }

    private static String formatId(int id) {
        if (id > 99) return String.valueOf(id);
        if (id > 9) return "0"+String.valueOf(id);
        return "00"+String.valueOf(id);
    }
}
