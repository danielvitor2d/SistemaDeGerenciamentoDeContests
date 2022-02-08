package src.view;

import java.io.IOException;
import java.util.Scanner;

import static src.view.TeamsMenu.teamsMenu;
import static src.view.PeopleMenu.peopleMenu;
import static src.view.ContestsMenu.contestMenu;
import static src.view.SubmissionsMenu.submissionsMenu;
import static src.view.ProblemsMenu.problemsMenu;

public class App {
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String args[]) throws IOException {
		mainMenu();
	}
	
  public static void printHeader() {
  	clearConsole();
    System.out.println("\t\033[1;34m-----------------------------------------------\033[0m\t");
    System.out.println("\t\033[1;96mSistema de Gerenciamento de Contests versão 1.0\033[0m\t");
    System.out.println("\t\033[1;34m-----------------------------------------------\033[0m\t\n");
  }
	
  public static void clearConsole() {
    System.out.print("\033[H\033[J");
    System.out.flush();
  }

  public static void clearBuffer() {
    if (input.hasNextLine()) {
      input.nextLine();
    }
  }

  public static void waitEnter() throws IOException {
    System.in.read();
  }
  
  public static String formatId(int id) {
    if (id > 99) return String.valueOf(id);
    if (id > 9) return "0"+String.valueOf(id);
    return "00"+String.valueOf(id);
  }

  public static void mainMenu() throws IOException {
  	int optionMenu = 0;
  	do {
  		printHeader();
  		
  		System.out.println("\033[0;34m1) Gerenciamento de pessoas\033[0m\n" +
  											 "\033[0;34m2) Gerenciamento de times\033[0m\n" +
  											 "\033[0;34m3) Gerenciamento de contests\033[0m\n" +
												 "\033[0;34m4) Gerenciamento de problemas\033[0m\n" +
  											 "\033[0;34m5) Gerenciamento de submissões\033[0m\n" +
  											 "\033[0;34m6) Sair do Sistema\033[0m\n");
  		
  		optionMenu = input.nextInt();
  		clearBuffer();
  		
  		try {
    		switch(optionMenu) {
    			case 1:
						peopleMenu();
    				break;
    			case 2:
    				teamsMenu();
    				break;
    			case 3:
						contestMenu();
    				break;
					case 4:
						problemsMenu();
						break;
    			case 5:
						submissionsMenu();
    				break;
    			case 6:
    				printHeader();
            System.out.println("Finalizando o sistema. Aguarde...");
    				break;
    			default:
    				System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
    				waitEnter();
						continue;
    		}
			} catch (IOException e) {
				System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
			}
  		
  	} while (optionMenu != 6);
  }
}
