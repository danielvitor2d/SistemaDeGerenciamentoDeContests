import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Teste {
  private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
  private static GregorianCalendar gregorianCalendar = new GregorianCalendar();

  public static void main(String args[]) {
    // try {
    //   gregorianCalendar.setTime(dateFormatter.parse("31/10/1999 09:35"));
    //   System.out.println(gregorianCalendar.getTime());

    // } catch (ParseException e) {
    //   System.out.println("Formato inválido");
    // }

    System.out.println ("---- Espaços à esquerda - exemplo:  %5d ");
    for (int i = 1; i <= 10; ++i) {
        System.out.printf ("%2d - [%" + i + "d]%n", i, 123);
    }
    System.out.println ("---- Espaços à direita - exemplo:  %-5d ");
    for (int i = 1; i <= 10; ++i) {
        System.out.printf ("%2d - [%-" + i + "d]%n", i, 123);
    }
    System.out.println ("---- Zeros à esquerda - exemplo:  %05d ");
    for (int i = 1; i <= 10; ++i) {
        System.out.printf ("%2d - [%0" + i + "d]%n", i, 123);
    }
    System.out.println ("---- Separadores de milhar - exemplo: %,5d ");
    for (int i = 1; i <= 10; ++i) {
        System.out.printf ("%2d - [%," + i + "d]%n", i, 123456);
    }
  }
}
