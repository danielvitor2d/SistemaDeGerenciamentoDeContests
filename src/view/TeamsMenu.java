package view;

import static view.App.input;
import static view.App.printHeader;
import static view.App.waitEnter;

import java.io.IOException;
import java.util.List;

import model.bean.Person;
import model.bean.PersonType;
import model.bean.Team;
import model.bean.TeamAndContestRegistered;
import model.bean.TeamAndCountOfSubmissions;
import model.bean.ViewTeam;
import model.dao.PersonDAO;
import model.dao.TeamDAO;

import static view.App.clearBuffer;
import static view.App.clearConsole;
import static view.App.formatId;

public class TeamsMenu {
  private static int optionMenu = 0;

  public static void teamsMenu() throws IOException {
    do {
      printHeader();

      System.out.println("\033[0;34m1) Criar time\033[0m\n" +
          "\033[0;34m2) Listar times\033[0m\n" +
          "\033[0;34m3) Editar time\033[0m\n" +
          "\033[0;34m4) Listar times com nomes dos membros\033[0m\n" +
          "\033[0;34m5) Listar times e somatório da quantidade de problemas nos contests\033[0m\n" +
          "\033[0;34m6) Listar times e quantidade total de submissões\033[0m\n" +
          "\033[0;34m7) Retornar\033[0m\n");

      optionMenu = input.nextInt();
      clearBuffer();

      try {
        switch (optionMenu) {
          case 1:
            createTeam();
            break;
          case 2:
            listTeams();
            break;
          case 3:
            updateTeam();
            break;
          case 4:
            listarTimeComNomesDosMembros();
            break;
          case 5:
            listarTimeQuantidadeDeProblemasEmTodosOsContests();
            break;
          case 6:
            listarTimeEQuantidadeDeSubmissoes();
            break;
          case 7:
            break;
          default:
            System.out.println("Opção inválida! \nAperte \033[1;32mENTER\033[0m para tentar novamente");
            waitEnter();
            continue;
        }
      } catch (IOException e) {
        System.out.println("Opção inválida! \nAperte \033[1;32mENTER\033[0m para tentar novamente");
        waitEnter();
        clearConsole();
      }

    } while (optionMenu != 7);
  }

  public static void createTeam() throws IOException {
    Team team = new Team();
    TeamDAO teamDAO = new TeamDAO();
    PersonDAO personDAO = new PersonDAO();

    // Lendo o nome do time
    while (true) {
      try {
        printHeader();
        System.out.print("Digite o nome do time:\n> ");
        team.setTeamName(input.nextLine());
        if (team.getTeamName().isEmpty()) {
          System.out.println("O nome do time deve ter no mínimo 1 caractere!" +
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

    // Lendo a url da foto do time
    printHeader();
    System.out.print("Digite a url da foto do time. Dê \033[1;32mENTER\033[0m para deixar vazio:\n> ");
    team.setTeamPhotoUrl(input.nextLine());
    clearConsole();

    // Lendo ID do primeiro aluno do time
    while (true) {
      try {
        printHeader();

        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
          System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o ID do primeiro aluno do time:\n> ");
        team.setStudentId01(input.nextInt());

        Person person = personDAO.getById(team.getStudentId01());

        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (!person.getPersonType().equals(PersonType.STUDENT)) {
          System.out.println("Pessoa escolhida não é um aluno!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        clearBuffer();
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo ID do segundo aluno do time
    while (true) {
      try {
        printHeader();

        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
          System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o ID do segundo aluno do time:\n> ");
        team.setStudentId02(input.nextInt());

        Person person = personDAO.getById(team.getStudentId02());

        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (!person.getPersonType().equals(PersonType.STUDENT)) {
          System.out.println("Pessoa escolhida não é um aluno!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (person.getPersonId() == team.getStudentId01()) {
          System.out.println("Pessoa já escolhida para o time!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        clearBuffer();
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo ID do terceiro aluno do time
    while (true) {
      try {
        printHeader();

        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
          System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o ID do terceiro aluno do time:\n> ");
        team.setStudentId03(input.nextInt());

        Person person = personDAO.getById(team.getStudentId03());

        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (!person.getPersonType().equals(PersonType.STUDENT)) {
          System.out.println("Pessoa escolhida não é um aluno!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (person.getPersonId() == team.getStudentId01() || person.getPersonId() == team.getStudentId02()) {
          System.out.println("Pessoa já escolhida para o time!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        clearBuffer();
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo ID do coach
    while (true) {
      try {
        printHeader();

        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
          System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o ID do coach:\n> ");
        team.setCoachId(input.nextInt());

        Person person = personDAO.getById(team.getCoachId());

        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (!person.getPersonType().equals(PersonType.COACH)) {
          System.out.println("Pessoa escolhida não é um coach!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        clearBuffer();
        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    printHeader();

    if (teamDAO.save(team)) {
      System.out.println("O time foi criado com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao criar time!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void listTeams() throws IOException {
    printHeader();

    TeamDAO teamDAO = new TeamDAO();
    List<Team> teams = teamDAO.listTeams();

    System.out.println("Listando times cadastrados:");
    System.out.printf("\033[1;34m%-10s|%-20s\033[1m%n", "ID", "Nome do Time");
    for (Team team : teams) {
      System.out.printf("\033[0;34m%-10s|%-20s\033[0m%n", formatId(team.getTeamId()), team.getTeamName());
    }
    
    if (teams.size() == 0) {
      System.out.println("Nenhum time cadastrado!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    System.out.println("\nPressione \033[1;32mENTER\033[0m para voltar.");

    waitEnter();
    clearConsole();
  }

  public static void removeTeam() throws IOException {
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

    if (teamDAO.delete(team.getTeamId())) {
      System.out.println("O time foi removido com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao remover time!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void updateTeam() throws IOException {
    Team team = null;
    TeamDAO teamDAO = new TeamDAO();
    PersonDAO personDAO = new PersonDAO();

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

    // Lendo o nome do time
    while (true) {
      printHeader();
      System.out.print("Digite o novo nome do time. Dê \033[1;32mENTER\033[0m para não alterar:\n> ");
      String new_name = input.nextLine();
      if (!new_name.isEmpty()) {
        team.setTeamName(new_name);
        clearConsole();
        break;
      }
      clearConsole();
      break;
    }

    // Lendo a url da foto do time
    while (true) {
      printHeader();
      System.out.print("Digite a url da foto do time. Dê \033[1;32mENTER\033[0m para deixar vazio:\n> ");
      String new_url = input.nextLine();
      if (!new_url.isEmpty()) {
        team.setTeamPhotoUrl(input.nextLine());
        clearConsole();
        break;
      }
      clearConsole();
      break;
    }

    // Lendo ID do primeiro aluno do time
    while (true) {
      try {
        printHeader();

        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
          System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o novo ID do primeiro aluno do time. Digite -1 para não alterar:\n> ");
        int new_id = input.nextInt();
        clearBuffer();

        if (new_id == -1) {
          clearConsole();
          break;
        }

        Person person = personDAO.getById(new_id);

        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (!person.getPersonType().equals(PersonType.STUDENT)) {
          System.out.println("Pessoa escolhida não é um aluno!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        team.setStudentId01(new_id);

        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo ID do segundo aluno do time
    while (true) {
      try {
        printHeader();

        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
          System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o novo ID do segundo aluno do time. Digite -1 para não alterar:\n> ");
        int new_id = input.nextInt();
        clearBuffer();

        if (new_id == -1) {
          clearConsole();
          break;
        }

        Person person = personDAO.getById(new_id);

        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (!person.getPersonType().equals(PersonType.STUDENT)) {
          System.out.println("Pessoa escolhida não é um aluno!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        team.setStudentId02(new_id);

        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo ID do terceiro aluno do time
    while (true) {
      try {
        printHeader();

        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
          System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o novo ID do terceiro aluno do time. Digite -1 para não alterar:\n> ");
        int new_id = input.nextInt();
        clearBuffer();

        if (new_id == -1) {
          clearConsole();
          break;
        }

        Person person = personDAO.getById(new_id);

        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (!person.getPersonType().equals(PersonType.STUDENT)) {
          System.out.println("Pessoa escolhida não é um aluno!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        team.setStudentId03(new_id);

        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    // Lendo ID do coach
    while (true) {
      try {
        printHeader();

        List<Person> people = personDAO.listPeople();

        System.out.println("Listando pessoas cadastradas:");
        for (Person person : people) {
          System.out.println("- " + formatId(person.getPersonId()) + " | " + person.getPersonType() + " | " + person.getName());
        }

        if (people.size() == 0) {
          System.out.println("Nenhuma pessoa cadastrada!\nPressione \033[1;32mENTER\033[0m para voltar.");
          waitEnter();
          clearConsole();
          return;
        }

        System.out.print("Digite o novo ID do coach. Digite -1 para não alterar:\n> ");
        int new_id = input.nextInt();
        clearBuffer();

        if (new_id == -1) {
          clearConsole();
          break;
        }

        Person person = personDAO.getById(new_id);

        if (person == null) {
          System.out.println("ID inválido!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        if (!person.getPersonType().equals(PersonType.COACH)) {
          System.out.println("Pessoa escolhida não é um coach!\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
          waitEnter();
          clearConsole();
          continue;
        }

        team.setCoachId(new_id);

        clearConsole();
        break;
      } catch (IOException e) {
        System.out.println("\nPressione \033[1;32mENTER\033[0m para tentar de novo.");
        waitEnter();
        clearConsole();
      }
    }

    printHeader();

    if (teamDAO.update(team.getTeamId(), team)) {
      System.out.println("O time foi alterado com sucesso!\nPressione \033[1;32mENTER\033[0m para voltar.");
    } else {
      System.out.println("Erro ao alterar time!\nPressione \033[1;32mENTER\033[0m para voltar.");
    }

    waitEnter();
    clearConsole();
  }

  public static void listarTimeComNomesDosMembros() throws IOException {
    printHeader();

    TeamDAO teamDAO = new TeamDAO();

    List<ViewTeam> viewTeams = teamDAO.listarTimeComNomesDosMembros();

    System.out.printf("\033[1;34m%-25s|%-25s|%-25s|%-25s\033[1m%n", "Aluno 01", "Aluno 02", "Aluno 03", "Coach");
    for (ViewTeam viewTeam : viewTeams) {
      System.out.printf("\033[0;34m%-25s|%-25s|%-25s|%-25s\033[0m%n", viewTeam.getStudent01(), viewTeam.getStudent02(), viewTeam.getStudent03(), viewTeam.getCoach());
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
