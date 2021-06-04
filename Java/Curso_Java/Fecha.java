import java.util.Calendar;

public class Fecha {
   
    public static void main (String[] args) {
    	String meses [] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Sempiembre","Octubre","Noviembre","Diciembre"};
    	Calendar actual = Calendar.getInstance();
    	System.out.print("La fecha del sistema es: ");
    	System.out.print(actual.get(Calendar.DATE));
    	System.out.print(" "+meses[actual.get(Calendar.MONTH)]);
    	System.out.println(" "+actual.get(Calendar.YEAR));
    	System.out.print("La hora del sistema es :");
    	System.out.print(actual.get(Calendar.HOUR));
    	System.out.print(":"+actual.get(Calendar.MINUTE));
    	System.out.print(":"+actual.get(Calendar.SECOND));
    }
}