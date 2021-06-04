package Modelo.Obra;

public class Poliptico extends Panel {
    
    public  Poliptico(int n) throws Exception{
        super();
        if (n>= 4) {
            this.numero_paneles = n;
        } else {
            throw new Exception("N debe ser mayor o igual a 4"); 
        } 
    }
    
}
