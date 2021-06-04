import java.io.*;

public class crearArchivo
 {
 	public static void main (String[] args) 
 	{
 		//File archivo = new File ("D:/Mis Documentos/javaVanessa/clase6/archivo.txt");
 		File archivo = new File ("D:/Mis Documentos/JavaSantiagoM","prueba.txt");
 		 try 
 		 {
        //A partir del objeto File creamos el fichero físicamente
          if (archivo.createNewFile())
           System.out.println("El archivo se ha creado correctamente");
          else
           System.out.println("No ha podido ser creado el archivo");
           } catch (IOException ioe) 
         	{
               ioe.printStackTrace();
            }
          }
         }
