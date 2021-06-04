package Juego;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ghosts extends Thread{
    Map M;
    public int []Ghost;
    public int GPN;
    public int GPM;
    char id;
    
    public Ghosts(int x, int y, char i){
        Ghost=new int[2];
        Ghost[0]=x;
        GPN=x;
        Ghost[1]=y;
        GPM=y;
        id=i;
        M = Map.getInstance();
    }
    
    public void run(){
        char anterior=' ';
        
        while(M.getVidas()>0||M.getComidas()>0){
            try {
                sleep(250);
                if(M.comer==0) anterior=M.moverGhost(id, anterior);
                if(M.comer==1) anterior=M.moverGhostB(id, anterior);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ghosts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
