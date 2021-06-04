
package Controlador;
import Data.Data;
import Vista.Admin.IAcceso;

public class Ctrl_Acceso {
    //MÉTODO MAIN
    public static void main(String args[]){          
        //INSTANCIACIÓN INICIAL DE INTERFACES
        IAcceso iAcceso = new IAcceso();
        iAcceso.setVisible(true);
        iAcceso.setLocationRelativeTo(null);
        
        //INSTANCIACIÓN INCIAL DE DATA
        Data data = new Data();
        
        //INSTANCIACIÓN INICIAL DE CONTROLADORES
        Admin_O ctrlObras = Admin_O.getInstance();
        Admin_PI ctrlPI = Admin_PI.getInstance();
        Ctrl_AdminTour ctrlTour = Ctrl_AdminTour.instance();
        Ctrl_Visitante ctrlVisitante = Ctrl_Visitante.instance();
        
        
    }
}
