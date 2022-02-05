package view;

import java.io.IOException;

import static view.App.input;
import static view.App.printHeader;
import static view.App.waitEnter;
import static view.App.clearBuffer;
import static view.App.clearConsole;

public class PersonsMenu {
  public static void personsMenu() {
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
            printHeader();
            System.out.println("Menu de adicionar pessoa");
            System.out.println("Aperte \033[1;32mENTER\033[0m para voltar.");
            waitEnter();
            break;
          case 2:
            printHeader();
            System.out.println("Menu de listar pessoas");
            System.out.println("Aperte \033[1;32mENTER\033[0m para voltar.");
            waitEnter();
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
}
