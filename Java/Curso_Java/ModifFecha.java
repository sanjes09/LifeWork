import java.util.Calendar;

public class ModifFecha {
	public static void main (String[] args) {
		String meses [] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Sempiembre","Octubre","Noviembre","Diciembre"};
		String AM_PM[]={"AM","PM"};
		//creacion de objetio tipo calendar
		Calendar actual = Calendar.getInstance();
		//se establece una hora
		actual.set(Calendar.HOUR, 14);
		//muestra la hora
		System.out.print("La hora es: "+actual.get(Calendar.HOUR_OF_DAY));
		System.out.println(" "+AM_PM[actual.get(Calendar.AM_PM)]);
		//se establece dia, mes y año
		actual.set(2000,11,24);
		System.out.print("La fecha establecida es: ");
		System.out.print(actual.get(Calendar.DATE));
		System.out.print(" "+meses[actual.get(Calendar.MONTH)]);
		System.out.println(" "+actual.get (Calendar.YEAR));		

	}

}