public class Stringnumero {
	public static void main (String[] args) {
		String Sbyte="10";
		String Sshort ="105";
		String Sint="10879";
		String Slong ="45987632";
		String Sfloat="15.42";
		String Sdouble="1.32569741009";
		System.out.println("De String a byte: "+Byte.parseByte(Sbyte));
		System.out.println("De String a short: "+Short.parseShort(Sshort));
		System.out.println("De String a int: "+Integer.parseInt(Sint));
		System.out.println("De String a long: "+Long.parseLong(Slong));
		System.out.println("De String a float: "+Float.parseFloat(Sfloat));
		System.out.println("De String a double: "+Double.parseDouble(Sdouble));
	}    
}
