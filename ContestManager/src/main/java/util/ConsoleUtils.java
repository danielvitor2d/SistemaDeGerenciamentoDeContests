package util;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUtils {
    public static Scanner input = new Scanner(System.in);
    public static void clearConsole() {
        System.out.print("\033[H\033[J");
        System.out.flush();
    }
    public static void printHeader() {
        clearConsole();
        System.out.println("\t\033[1;34m-----------------------------------------------\033[0m\t");
        System.out.println("\t\033[1;96mSistema de Gerenciamento de Contests versão 1.0\033[0m\t");
        System.out.println("\t\033[1;34m-----------------------------------------------\033[0m\t\n");
    }
    public static void clearBuffer() {
        if (input.hasNextLine()) {
            input.nextLine();
        }
    }
    public static void waitEnter() throws IOException {
        System.in.read();
    }
}
