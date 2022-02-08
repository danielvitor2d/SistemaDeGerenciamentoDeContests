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
import static view.App.formatId;

public class PeopleMenu {
  private static int optionMenu = 0;

  public static void peopleMenu() throws IOException {
    do {
      printHeader();
      
      System.out.println("\033[0;34m1) Adicionar pessoa\033[0m\n" +
                          "\033[0;34m2) Listar pessoas\033[0m\n" +
                          "\033[0;34m3) Listar dados da pessoa\033[0m\n" +
                          "\033[0;34m4) Atualizar pessoa\033[0m\n" +
                          "\033[0;34m5) Remover pessoa\033[0m\n" +
                          "\033[0;34m6) Retornar\033[0m\n");
      
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
            getPerson();
            break;
          case 4:
            updatePerson();
            break;
          case 5:
            removePerson();
            break;
          case 6:
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
      
    } while (optionMenu != 6);
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
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
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
        if (person.getAge() < 0) {
          System.out.println("Idade inválida!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
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
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
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
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
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
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
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

    printHeader();

    PersonDAO personDAO = new PersonDAO();
    if (personDAO.save(person)) {
      System.out.println("Pessoa cadastrada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao cadastrar pessoa!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void listPeople() throws IOException {
    printHeader();

    PersonDAO personDAO = new PersonDAO();
    List<Person> people = personDAO.listPeople();
    
    System.out.println("Listando pessoas cadastradas:");
    System.out.printf("\033[1;34m%-5s|%-17s|%-20s\033[1m%n", "ID", "Tipo de Pessoa", "Nome da Pessoa");
    for (Person person : people) {
      String personType = (person.getPersonType().equals(PersonType.STUDENT) ? "Aluno" : (person.getPersonType().equals(PersonType.COACH) ? "Coach" : "Juiz"));
      System.out.printf("\033[0;34m%-5s|%-17s|%-20s\033[0m%n", formatId(person.getPersonId()), personType, person.getName());
    }

    if (people.size() == 0) {
      System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
      waitEnter();
      clearConsole();
      return;
    }

    System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");
    waitEnter();
    clearConsole();
  }

  public static void getPerson() throws IOException {
    Person person = null;
    PersonDAO personDAO = new PersonDAO();
    List<Person> people = personDAO.listPeople();

    while (true) {
      try {
        printHeader();
    
        System.out.println("Listando pessoas cadastradas:");
        System.out.printf("\033[1;34m%-5s|%-17s|%-20s\033[1m%n", "ID", "Tipo de Pessoa", "Nome da Pessoa");
        for (Person _person : people) {
          String personType = (_person.getPersonType().equals(PersonType.STUDENT) ? "Aluno" : (_person.getPersonType().equals(PersonType.COACH) ? "Coach" : "Juiz"));
          System.out.printf("\033[0;34m%-5s|%-17s|%-20s\033[0m%n", formatId(_person.getPersonId()), personType, _person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o ID da pessoa:\n> ");
        int personId = input.nextInt();
        person = personDAO.getById(personId);
        if (person == null) {
          throw new IndexOutOfBoundsException();
        }
        clearConsole();
        break;

      } catch (IndexOutOfBoundsException e) {
        System.out.println("Pessoa não existe\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    printHeader();

    System.out.println("Nome: " + person.getName());
    System.out.println("Idade: " + person.getAge());
    System.out.println("E-mail: " + person.getEmail());
    System.out.println("Telefone: " + person.getPhone());
    System.out.println("Universidade: " + person.getUniversity());
    String personType = person.getPersonType().toString();
    System.out.println("Tipo de Pessoa: " + (personType.equals("STUDENT") ? "Aluno" : (personType.equals("COACH") ? "Coach" : "Juiz")));

    System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

    waitEnter();
    clearConsole();
  }

  public static void updatePerson() throws IOException {
    Person person = null;
    PersonDAO personDAO = new PersonDAO();
    List<Person> people = personDAO.listPeople();

    while (true) {
      try {
        printHeader();
    
        System.out.println("Listando pessoas cadastradas:");
        System.out.printf("\033[1;34m%-5s|%-17s|%-20s\033[1m%n", "ID", "Tipo de Pessoa", "Nome da Pessoa");
        for (Person _person : people) {
          String personType = (_person.getPersonType().equals(PersonType.STUDENT) ? "Aluno" : (_person.getPersonType().equals(PersonType.COACH) ? "Coach" : "Juiz"));
          System.out.printf("\033[0;34m%-5s|%-17s|%-20s\033[0m%n", formatId(_person.getPersonId()), personType, _person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o ID da pessoa:\n> ");
        int personId = input.nextInt();
        clearBuffer();
        person = personDAO.getById(personId);
        if (person == null) {
          throw new IndexOutOfBoundsException();
        }
        clearConsole();
        break;
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Pessoa não existe\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo nome da pessoa
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o nome da pessoa. Dê \033[1;32mENTER\033[0m caso não deseje alterar:\n> ");
        String new_name = input.nextLine();
        if (!new_name.isEmpty()) {
          person.setName(new_name);
        }
        clearConsole();
        break;
      } catch (Exception e) {
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo idade da pessoa
    while (true) {
      try {
        printHeader();
        System.out.print("Digite a idade da pessoa. Digite -1 caso não deseje alterar:\n> ");
        int new_age = input.nextInt();
        clearBuffer();
        if (new_age != -1) {
          person.setAge(new_age);
        }
        clearConsole();
        break;
      } catch (Exception e) {
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo email da pessoa
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o e-mail da pessoa. Dê \033[1;32mENTER\033[0m caso não deseje alterar:\n> ");
        String new_email = input.nextLine();
        if (!new_email.isEmpty()) {
          person.setEmail(new_email);
        }
        clearConsole();
        break;
      } catch (Exception e) {
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo telefone da pessoa
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o telefone da pessoa. Dê \033[1;32mENTER\033[0m caso não deseje alterar:\n> ");
        String new_phone = input.nextLine();
        if (new_phone.isEmpty()) {
          person.setPhone(new_phone);
        }
        clearConsole();
        break;
      } catch (Exception e) {
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo universidade da pessoa
    while (true) {
      try {
        printHeader();
        System.out.print("Digite a universidade da pessoa. Dê \033[1;32mENTER\033[0m caso não deseje alterar:\n> ");
        String new_university = input.nextLine();
        if (new_university.isEmpty()) {
          person.setUniversity(new_university);
        }
        clearConsole();
        break;
      } catch (Exception e) {
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo tipo da pessoa
    while (true) {
      try {
        printHeader();
        System.out.print("Insira o tipo da pessoa (A - Aluno, C - Coach, J - Juiz). Dê \033[1;32mENTER\033[0m caso não deseje alterar:\n> ");
        String letra = input.nextLine();
        if (letra.isEmpty()) {
          clearConsole();
          break;
        }
        letra = letra.toUpperCase();
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

    printHeader();

    if (personDAO.update(person.getPersonId(), person)) {
      System.out.println("Pessoa atualizada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao atualizar pessoa!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void removePerson() throws IOException {
    PersonDAO personDAO = new PersonDAO();
    int ID = 0;
    Person person = null;

    List<Person> people = personDAO.listPeople();

    while(true) {
      try {
        printHeader();

        System.out.println("Listando pessoas cadastradas:");
        System.out.printf("\033[1;34m%-5s|%-17s|%-20s\033[1m%n", "ID", "Tipo de Pessoa", "Nome da Pessoa");
        for (Person _person : people) {
          String personType = (_person.getPersonType().equals(PersonType.STUDENT) ? "Aluno" : (_person.getPersonType().equals(PersonType.COACH) ? "Coach" : "Juiz"));
          System.out.printf("\033[0;34m%-5s|%-17s|%-20s\033[0m%n", formatId(_person.getPersonId()), personType, _person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Insira o ID da pessoa:\n> ");
        ID = input.nextInt();
        clearBuffer();
        person = personDAO.getById(ID);
        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");  
          waitEnter();
          clearConsole();
          continue;
        }
        clearConsole();
        break;
      } catch (Exception e) {
        System.out.println("Erro!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    printHeader();

    if (personDAO.delete(person.getPersonId())) {
      System.out.println("A pessoa foi deletada com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao deletar pessoa!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }
}