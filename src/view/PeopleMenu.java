package view;

import java.io.IOException;
import java.util.List;

import model.bean.Person;
import model.bean.PersonType;

import model.dao.PersonDAO;

import static view.App.input;
import static view.App.printHeader;
import static view.App.waitEnter;
import static view.App.clearBuffer;
import static view.App.clearConsole;

public class PeopleMenu {
  public static void peopleMenu() {
    int optionMenu = 0;
    do {
      printHeader();
      
      System.out.println("\033[0;34m1) Adicionar pessoa\033[0m\n" +
                          "\033[0;34m2) Listar pessoas\033[0m\n" +
                          "\033[0;34m3) Atualizar pessoa\033[0m\n" +
                          "\033[0;34m4) Remover pessoa\033[0m\n" +
                          "\033[0;34m5) Retornar\033[0m\n");
      
      optionMenu = input.nextInt();
      clearBuffer();
      
      try {
        switch(optionMenu) {
          case 1:
            addPerson();
            break;
          case 2:
            listPeople();
            break;
          case 3:
            printHeader();
            System.out.println("Menu de atualizar pessoa");
            System.out.println("Aperte \033[1;32mENTER\033[0m para voltar.");
            waitEnter();
            break;
          case 4:
            printHeader();
            System.out.println("Menu de remover pessoa");
            System.out.println("Aperte \033[1;32mENTER\033[0m para voltar.");
            waitEnter();
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

  public static void addPerson() throws IOException {
    Person person = new Person();

    // Lendo nome da pessoa
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o nome da pessoa:\n> ");
        person.setName(input.nextLine());
        if (person.getName().isEmpty()) {
          System.out.println("Nome inválido! O nome do aluno deve ter no mínimo 1 caractere!" + 
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

    // Lendo idade da pessoa
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

  private static String formatId(int id) {
    if (id > 99) return String.valueOf(id);
    if (id > 9) return "0"+String.valueOf(id);
    return "00"+String.valueOf(id);
  }
}