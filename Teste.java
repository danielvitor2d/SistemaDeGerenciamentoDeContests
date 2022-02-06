import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Teste {
  private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
  private static GregorianCalendar gregorianCalendar = new GregorianCalendar();

  public static void main(String args[]) {
    try {
      gregorianCalendar.setTime(dateFormatter.parse("31/10/1999 09:35"));
      System.out.println(gregorianCalendar.getTime());

    } catch (ParseException e) {
      System.out.println("Formato inválido");
    }
  }
}
