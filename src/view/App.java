package view;

import java.io.IOException;
// import java.util.Locale;
import java.util.Scanner;

import static view.TeamsMenu.teamsMenu;

public class App {
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String args[]) {
		// Locale.setDefault(Locale.US);
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
  
  public static void mainMenu() {
  	int optionMenu = 0;
  	do {
  		printHeader();
  		
  		System.out.println("\033[0;34m1) Consultas de pessoas\033[0m\n" +
  											 "\033[0;34m2) Consultas de times\033[0m\n" +
  											 "\033[0;34m3) Consultas de contests\033[0m\n" +
  											 "\033[0;34m4) Consultas de submissões\033[0m\n" +
  											 "\033[0;34m5) Sair do Sistema\033[0m\n");
  		
  		optionMenu = input.nextInt();
  		clearBuffer();
  		
  		try {
    		switch(optionMenu) {
    			case 1:
    				break;
    			case 2:
    				teamsMenu();
    				break;
    			case 3:
    				printHeader();
    				System.out.println("Menu de gerenciar contests");
            System.out.println("Aperte \033[1;32mENTER\033[0m para voltar.");
            waitEnter();
    				break;
    			case 4:
    				printHeader();
    				System.out.println("Menu de realizar submissões");
            System.out.println("Aperte \033[1;32mENTER\033[0m para voltar.");
            waitEnter();
    				break;
    			case 5:
    				printHeader();
            System.out.println("Finalizando o sistema. Aguarde...");
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
}
