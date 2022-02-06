package view;

import util.ConsoleUtils;

import java.io.IOException;

public class App {
  public static void mainMenu() {
  	int optionMenu = 0;
  	do {
  		ConsoleUtils.printHeader();
  		
  		System.out.println("\033[0;34m1) Gerenciar pessoas\033[0m\n" +
  											 "\033[0;34m2) Gerenciar times\033[0m\n" +
  											 "\033[0;34m3) Gerenciar contests\033[0m\n" +
  											 "\033[0;34m4) Realizar submissões\033[0m\n" +
  											 "\033[0;34m5) Sair do Sistema\033[0m\n");

  		optionMenu = ConsoleUtils.input.nextInt();
		ConsoleUtils.clearBuffer();
  		
  		try {
    		switch(optionMenu) {
    			case 1:
    				PeopleMenu.menu();
    				break;
    			case 2:
					TeamsMenu.menu();
    				break;
    			case 3:
					ContestsMenu.menu();
    				break;
    			case 4:
					SubmissionsMenu.menu();
    				break;
    			case 5:
					ConsoleUtils.printHeader();
            		System.out.println("Finalizando o sistema. Aguarde...");
    				break;
    			default:
    				System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
					ConsoleUtils.waitEnter();
    		}
		} catch (IOException e) {
			System.out.println("Opção inválida! Aperte \033[1;32mENTER\033[0m para tentar novamente");
		}
  	} while (optionMenu != 5);
  }
}
