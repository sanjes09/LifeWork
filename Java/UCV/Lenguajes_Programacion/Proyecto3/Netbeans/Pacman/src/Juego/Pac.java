package Juego;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pac extends Thread {
    Map M;
    public int []Pac;
    char id='P';
    int siguiente;
    char anterior=' ';
    int comer=0;
    int cont=0;
    
    public Pac(int x, int y, int z){
        Pac=new int[2];
        Pac[0]=x;
        Pac[1]=y;
        siguiente=(int) (Math.random() * 4) + 1;
        M = Map.getInstance();
    }
    
    public void setSiguiente(int s){
        siguiente=s;
    }
    
    public void run(){        
        while(M.getVidas()>0||M.getComidas()>0){
            try {
                sleep(250);
                if(comer==0){
                    anterior=M.moverPac(Pac,siguiente,anterior);
                    if(anterior=='*'){
                        comer=1;
                        cont=0;
                    }
                }
                if(comer==1){
                    cont++;
                    anterior=M.moverPacB(Pac,siguiente,anterior);
                    if(cont==20) comer=0;
                }
            } catch (InterruptedException ex){
                Logger.getLogger(Ghosts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
