import java.util.Calendar;

public class Ejercicio5 {
	public static void main (String[] args) {
		String meses [] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Sempiembre","Octubre","Noviembre","Diciembre"};
		String AM_PM[]={"AM","PM"};
		Calendar actual = Calendar.getInstance();
		int ran;
		ran=(int)(Math.random() *4) + 1;
		System.out.print("La hora es: "+actual.get(Calendar.HOUR_OF_DAY));
		System.out.println(" "+AM_PM[actual.get(Calendar.AM_PM)]);
		System.out.print("La fecha actual es: ");
		System.out.print(actual.get(Calendar.DATE));
		System.out.print(" "+meses[actual.get(Calendar.MONTH)]);
		System.out.println(" "+actual.get (Calendar.YEAR));
		System.out.println("Numero: "+ran);
		switch(ran){
			case 1: actual.set(Calendar.DATE, 19);
			        actual.set(Calendar.MONTH, 8);
			        actual.set(Calendar.YEAR, 1994);
			        System.out.print("La fecha actual es: ");
					System.out.print(actual.get(Calendar.DATE));
					System.out.print(" "+meses[actual.get(Calendar.MONTH)]);
					System.out.println(" "+actual.get (Calendar.YEAR));
			        break;			
			case 2: actual.set(Calendar.HOUR, 9);
			        System.out.print(actual.get(Calendar.HOUR));
    				System.out.print(":"+actual.get(Calendar.MINUTE));
    				System.out.print(":"+actual.get(Calendar.SECOND));
			        break;	
			case 3: actual.set(Calendar.MINUTE, 30);
					System.out.print(actual.get(Calendar.HOUR));
    				System.out.print(":"+actual.get(Calendar.MINUTE));
    				System.out.print(":"+actual.get(Calendar.SECOND));
			        break;				
			case 4: actual.set(Calendar.SECOND, 20);
					System.out.print(actual.get(Calendar.HOUR));
    				System.out.print(":"+actual.get(Calendar.MINUTE));
    				System.out.print(":"+actual.get(Calendar.SECOND));
    				break;
		}
	}
}