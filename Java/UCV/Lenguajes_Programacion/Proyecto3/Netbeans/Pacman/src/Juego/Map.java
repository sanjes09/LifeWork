package Juego;

import Vistas.*;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Map extends Thread{
    static Map uniqueInstance = null;
    Mesa Mesa1;
    IMenu Menu;
    public int n;
    public int m;
    public char [][]Mapa;
    public int dir;
    public int dir1;
    public int dir2;
    public int dir3;
    public int dir4;
    int Comidas;
    int Vidas;
    Pac Pacman;
    public int comer=0;
    int PN,PM;
    Ghosts[] G;
    
    private Map(int x, int y){
        n=x;
        m=y;
        Mapa=new char[x][y];
        G=new Ghosts[4];
    }
    
    static public Map getInstance(){
        return uniqueInstance;
    }
    
    static public Map instance(int x,int y){
        if (uniqueInstance==null){
            uniqueInstance=new Map(x, y);
        }
        return uniqueInstance;
    }
    
    protected void instancias(Mesa M, IMenu M2){
        Mesa1=M;
        Menu=M2;
    }
    
    protected void asignar(int x, int y, char j){
        Mapa[x][y]=j;
    }
    
    protected void imprimir(){
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                System.out.print(Mapa[i][j]);
            }
            System.out.println();
        }
    }
    
    protected void cargarComidas(int c){
        Comidas=c;
    }
    
    protected void cargarVidas(int v){
        Vidas=v;
    }
    
    protected void cargarCoordenadas(int x, int y){
        PN=x; PM=y;
    }
    
    protected void cargarTodo(Pac c, Ghosts T1, Ghosts T2, Ghosts T3, Ghosts T4){
        Pacman=c;
        G[0]=T1;
        G[1]=T2;
        G[2]=T3;
        G[3]=T4;
    }
    
    protected int getComidas(){
        return Comidas;
    }
    
    protected int getVidas(){
        return Vidas;
    }
    
    protected synchronized ArrayList<Integer> posibleEasy(int []Ghost){
        ArrayList<Integer> posibles = new ArrayList();
        //ARRIBA
        if((Ghost[0]==0) || ((Ghost[0]-1>=0) && (Mapa[Ghost[0]-1][Ghost[1]]!='#') && (Mapa[Ghost[0]-1][Ghost[1]]!='1') && (Mapa[Ghost[0]-1][Ghost[1]]!='2') && (Mapa[Ghost[0]-1][Ghost[1]]!='3') && (Mapa[Ghost[0]-1][Ghost[1]]!='4'))){
            posibles.add(1);
        }
        //ABAJO
        if((Ghost[0]==n-1) || ((Ghost[0]+1<n) && (Mapa[Ghost[0]+1][Ghost[1]]!='#') && (Mapa[Ghost[0]+1][Ghost[1]]!='1') && (Mapa[Ghost[0]+1][Ghost[1]]!='2') && (Mapa[Ghost[0]+1][Ghost[1]]!='3') && (Mapa[Ghost[0]+1][Ghost[1]]!='4'))){
            posibles.add(3);
        }
        //IZQUIERDA
        if((Ghost[1]==0) || ((Ghost[1]-1>=0) && (Mapa[Ghost[0]][Ghost[1]-1]!='#') && (Mapa[Ghost[0]][Ghost[1]-1]!='1') && (Mapa[Ghost[0]][Ghost[1]-1]!='2') && (Mapa[Ghost[0]][Ghost[1]-1]!='3') && (Mapa[Ghost[0]][Ghost[1]-1]!='4'))){
            posibles.add(4);
        }
        //DERECHA
        if((Ghost[1]==m-1) || ((Ghost[1]+1<m) && (Mapa[Ghost[0]][Ghost[1]+1]!='#') && (Mapa[Ghost[0]][Ghost[1]+1]!='1') && (Mapa[Ghost[0]][Ghost[1]+1]!='2') && (Mapa[Ghost[0]][Ghost[1]+1]!='3') && (Mapa[Ghost[0]][Ghost[1]+1]!='4'))){
            posibles.add(2);
        }
        return posibles;
    }
    
    protected synchronized char moverGhost(char id, char ant){
        int numero;
        int opcion;
        int idi;
        idi = Character.getNumericValue(id); 
        idi--;
        char anterior=ant;
        ArrayList<Integer> posibilidades;
        
        posibilidades=posibleEasy(G[idi].Ghost);
        
        if(!posibilidades.isEmpty()){
            numero = (int) (Math.random() * posibilidades.size()) + 1;
            opcion=posibilidades.get(numero-1);
        }
        else opcion=0;

        switch(opcion){
            //ARRIBA
            case 1: if(idi==0) dir1=1;
                    if(idi==1) dir2=1;
                    if(idi==2) dir3=1;
                    if(idi==3) dir4=1;
                    if(G[idi].Ghost[0]==0){
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[n-1][G[idi].Ghost[1]];
                        if(anterior=='P'){
                            Vidas--;
                            Mapa[Pacman.Pac[0]][Pacman.Pac[1]]=' ';
                            Pacman.Pac[0]=PN;
                            Pacman.Pac[1]=PM;
                            Mapa[PN][PM]='P';
                            anterior=' ';
                        }
                        Mapa[n-1][G[idi].Ghost[1]]=id;
                        G[idi].Ghost[0]=n-1;
                    }
                    else{
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]-1][G[idi].Ghost[1]];
                        if(anterior=='P'){
                            Vidas--;
                            Mapa[Pacman.Pac[0]][Pacman.Pac[1]]=' ';
                            Pacman.Pac[0]=PN;
                            Pacman.Pac[1]=PM;
                            Mapa[PN][PM]='P';
                            anterior=' ';
                        }
                        Mapa[G[idi].Ghost[0]-1][G[idi].Ghost[1]]=id;
                        G[idi].Ghost[0]--;
                    }
                break;
            //DERECHA
            case 2: if(idi==0) dir1=2;
                    if(idi==1) dir2=2;
                    if(idi==2) dir3=2;
                    if(idi==3) dir4=2;
                    if(G[idi].Ghost[1]==m-1){
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]][0];
                        if(anterior=='P'){
                            Vidas--;
                            Mapa[Pacman.Pac[0]][Pacman.Pac[1]]=' ';
                            Pacman.Pac[0]=PN;
                            Pacman.Pac[1]=PM;
                            Mapa[PN][PM]='P';
                            anterior=' ';
                        }
                        Mapa[G[idi].Ghost[0]][0]=id;
                        G[idi].Ghost[1]=0;
                    }
                    else{
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]+1];
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]+1]=id;
                        if(anterior=='P'){
                            Vidas--;
                            Mapa[Pacman.Pac[0]][Pacman.Pac[1]]=' ';
                            Pacman.Pac[0]=PN;
                            Pacman.Pac[1]=PM;
                            Mapa[PN][PM]='P';
                            anterior=' ';
                        }
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]+1]=id;
                        G[idi].Ghost[1]++;
                    }
                break;
            //ABAJO
            case 3: if(idi==0) dir1=3;
                    if(idi==1) dir2=3;
                    if(idi==2) dir3=3;
                    if(idi==3) dir4=3;
                    if(G[idi].Ghost[0]==n-1){
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[0][G[idi].Ghost[1]];
                        if(anterior=='P'){
                            Vidas--;
                            Mapa[Pacman.Pac[0]][Pacman.Pac[1]]=' ';
                            Pacman.Pac[0]=PN;
                            Pacman.Pac[1]=PM;
                            Mapa[PN][PM]='P';
                            anterior=' ';
                        }
                        Mapa[0][G[idi].Ghost[1]]=id;
                        G[idi].Ghost[0]=0;
                    }
                    else{
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]+1][G[idi].Ghost[1]];
                        if(anterior=='P'){
                            Vidas--;
                            Mapa[Pacman.Pac[0]][Pacman.Pac[1]]=' ';
                            Pacman.Pac[0]=PN;
                            Pacman.Pac[1]=PM;
                            Mapa[PN][PM]='P';
                            anterior=' ';
                        }
                        Mapa[G[idi].Ghost[0]+1][G[idi].Ghost[1]]=id;
                        G[idi].Ghost[0]++;
                    }
                break;
            //IZQUIERDA
            case 4: if(idi==0) dir1=4;
                    if(idi==1) dir2=4;
                    if(idi==2) dir3=4;
                    if(idi==3) dir4=4;
                    if(G[idi].Ghost[1]==0){
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]][m-1];
                        if(anterior=='P'){
                            Vidas--;
                            Mapa[Pacman.Pac[0]][Pacman.Pac[1]]=' ';
                            Pacman.Pac[0]=PN;
                            Pacman.Pac[1]=PM;
                            Mapa[PN][PM]='P';
                            anterior=' ';
                        }
                        Mapa[G[idi].Ghost[0]][m-1]=id;
                        G[idi].Ghost[1]=m-1;
                    }
                    else{
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]-1];
                        if(anterior=='P'){
                            Vidas--;
                            Mapa[Pacman.Pac[0]][Pacman.Pac[1]]=' ';
                            Pacman.Pac[0]=PN;
                            Pacman.Pac[1]=PM;
                            Mapa[PN][PM]='P';
                            anterior=' ';
                        }
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]-1]=id;
                        G[idi].Ghost[1]--;
                    }
                break;
            default:
                    try {
                        sleep(250);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Ghosts.class.getName()).log(Level.SEVERE, null, ex);
                    }
                break;
        }
        
        posibilidades.clear();       
        
        return anterior;
    }
    
    protected synchronized char moverGhostB(char id, char ant){
        int numero;
        int opcion;
        int idi;
        idi = Character.getNumericValue(id); 
        idi--;
        char anterior=ant;
        ArrayList<Integer> posibilidades;
        
        posibilidades=posibleEasy(G[idi].Ghost);
        
        if(!posibilidades.isEmpty()){
            numero = (int) (Math.random() * posibilidades.size()) + 1;
            opcion=posibilidades.get(numero-1);
        }
        else opcion=0;

        switch(opcion){
            //ARRIBA
            case 1: if(idi==0) dir1=1;
                    if(idi==1) dir2=1;
                    if(idi==2) dir3=1;
                    if(idi==3) dir4=1;
                    if(G[idi].Ghost[0]==0){
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[n-1][G[idi].Ghost[1]];
                        if(anterior=='P'){
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=' ';
                            G[idi].Ghost[0]=G[idi].GPN;
                            G[idi].Ghost[1]=G[idi].GPM;
                            anterior=' ';
                        }else{
                            Mapa[n-1][G[idi].Ghost[1]]=id;
                            G[idi].Ghost[0]=n-1;
                        }
                    }
                    else{
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]-1][G[idi].Ghost[1]];
                        if(anterior=='P'){
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=' ';
                            G[idi].Ghost[0]=G[idi].GPN;
                            G[idi].Ghost[1]=G[idi].GPM;
                            anterior=' ';
                        }
                        else{
                            Mapa[G[idi].Ghost[0]-1][G[idi].Ghost[1]]=id;
                            G[idi].Ghost[0]--;
                        }
                    }
                break;
            //DERECHA
            case 2: if(idi==0) dir1=2;
                    if(idi==1) dir2=2;
                    if(idi==2) dir3=2;
                    if(idi==3) dir4=2;
                    if(G[idi].Ghost[1]==m-1){
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]][0];
                        if(anterior=='P'){
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=' ';
                            G[idi].Ghost[0]=G[idi].GPN;
                            G[idi].Ghost[1]=G[idi].GPM;
                            anterior=' ';
                        }
                        else{
                            Mapa[G[idi].Ghost[0]][0]=id;
                            G[idi].Ghost[1]=0;
                        }
                    }
                    else{
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]+1];
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]+1]=id;
                        if(anterior=='P'){
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=' ';
                            G[idi].Ghost[0]=G[idi].GPN;
                            G[idi].Ghost[1]=G[idi].GPM;
                            anterior=' ';
                        }
                        else{
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]+1]=id;
                            G[idi].Ghost[1]++;
                        }
                    }
                break;
            //ABAJO
            case 3: if(idi==0) dir1=3;
                    if(idi==1) dir2=3;
                    if(idi==2) dir3=3;
                    if(idi==3) dir4=3;
                    if(G[idi].Ghost[0]==n-1){
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[0][G[idi].Ghost[1]];
                        if(anterior=='P'){
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=' ';
                            G[idi].Ghost[0]=G[idi].GPN;
                            G[idi].Ghost[1]=G[idi].GPM;
                            anterior=' ';
                        }
                        else{
                            Mapa[0][G[idi].Ghost[1]]=id;
                            G[idi].Ghost[0]=0;
                        }
                    }
                    else{
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]+1][G[idi].Ghost[1]];
                        if(anterior=='P'){
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=' ';
                            G[idi].Ghost[0]=G[idi].GPN;
                            G[idi].Ghost[1]=G[idi].GPM;
                            anterior=' ';
                        }
                        else{
                            Mapa[G[idi].Ghost[0]+1][G[idi].Ghost[1]]=id;
                            G[idi].Ghost[0]++;
                        }
                    }
                break;
            //IZQUIERDA
            case 4: if(idi==0) dir1=4;
                    if(idi==1) dir2=4;
                    if(idi==2) dir3=4;
                    if(idi==3) dir4=4;
                    if(G[idi].Ghost[1]==0){
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]][m-1];
                        if(anterior=='P'){
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=' ';
                            G[idi].Ghost[0]=G[idi].GPN;
                            G[idi].Ghost[1]=G[idi].GPM;
                            anterior=' ';
                        }
                        else{
                            Mapa[G[idi].Ghost[0]][m-1]=id;
                            G[idi].Ghost[1]=m-1;
                        }
                    }
                    else{
                        Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=anterior;
                        anterior=Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]-1];
                        if(anterior=='P'){
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]]=' ';
                            G[idi].Ghost[0]=G[idi].GPN;
                            G[idi].Ghost[1]=G[idi].GPM;
                            anterior=' ';
                        }
                        else{
                            Mapa[G[idi].Ghost[0]][G[idi].Ghost[1]-1]=id;
                            G[idi].Ghost[1]--;
                        }
                    }
                break;
            default:
                    try {
                        sleep(250);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Ghosts.class.getName()).log(Level.SEVERE, null, ex);
                    }
                break;
        }
        
        posibilidades.clear();       
        
        return anterior;
    }
    
    protected synchronized char moverPac(int [] pac,int s, char anterior){
        //ARRIBA
        dir=s;
        if(s==1){
            if(pac[0]==0){
                if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                else Mapa[pac[0]][pac[1]]=' ';
                anterior=Mapa[n-1][pac[1]];
                Mapa[n-1][pac[1]]='P';
                pac[0]=n-1;
            }else{
                if((pac[0]-1>=0) && (Mapa[pac[0]-1][pac[1]]!='#')){
                    if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                    else Mapa[pac[0]][pac[1]]=' ';
                    anterior=Mapa[pac[0]-1][pac[1]];
                    if(anterior!='1'&&anterior!='2'&&anterior!='3'&&anterior!='4'){
                        Mapa[pac[0]-1][pac[1]]='P';
                        pac[0]--;
                        if(anterior=='.'||anterior=='*') Comidas--;
                    }
                    else{
                        Vidas--;
                        Mapa[pac[0]][pac[1]]=' ';
                        pac[0]=PN;
                        pac[1]=PM;
                        Mapa[PN][PM]='P';
                    }
                }
            }
        }
        //DERECHA
        if(s==2){
            if(pac[1]==m-1){
                if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                else Mapa[pac[0]][pac[1]]=' ';
                anterior=Mapa[pac[0]][0];
                Mapa[pac[0]][0]='P';
                pac[1]=0;
            }else{
                if((pac[1]+1<m) && (Mapa[pac[0]][pac[1]+1]!='#')){
                    if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                    else Mapa[pac[0]][pac[1]]=' ';
                    anterior=Mapa[pac[0]][pac[1]+1];
                    if(anterior!='1'&&anterior!='2'&&anterior!='3'&&anterior!='4'){
                        Mapa[pac[0]][pac[1]+1]='P';
                        pac[1]++;
                        if(anterior=='.'||anterior=='*') Comidas--;
                    }
                    else{
                        Vidas--;
                        Mapa[pac[0]][pac[1]]=' ';
                        pac[0]=PN;
                        pac[1]=PM;
                        Mapa[PN][PM]='P';
                    }
                }
            }
        }
        //ABAJO
        if(s==3){
            if(pac[0]==n-1){
                if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                else Mapa[pac[0]][pac[1]]=' ';
                anterior=Mapa[0][pac[1]];
                Mapa[0][pac[1]]='P';
                pac[0]=0;
            }else{
                if((pac[0]+1<n) && (Mapa[pac[0]+1][pac[1]]!='#')){
                    if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                    else Mapa[pac[0]][pac[1]]=' ';
                    anterior=Mapa[pac[0]+1][pac[1]];
                    if(anterior!='1'&&anterior!='2'&&anterior!='3'&&anterior!='4'){
                        Mapa[pac[0]+1][pac[1]]='P';
                        pac[0]++;
                        if(anterior=='.'||anterior=='*') Comidas--;
                    }
                    else{
                        Vidas--;
                        Mapa[pac[0]][pac[1]]=' ';                        
                        pac[0]=PN;
                        pac[1]=PM;
                        Mapa[PN][PM]='P';
                    }
                }
            }
        }
        //IZQUIERDA
        if(s==4){
            if(pac[1]==0){
                if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                else Mapa[pac[0]][pac[1]]=' ';
                anterior=Mapa[pac[0]][m-1];
                Mapa[pac[0]][m-1]='P';
                pac[1]=m-1;
            }else{
                if((pac[1]-1>=0) && (Mapa[pac[0]][pac[1]-1]!='#')){
                    if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                    else Mapa[pac[0]][pac[1]]=' ';
                    anterior=Mapa[pac[0]][pac[1]-1];
                    if(anterior!='1'&&anterior!='2'&&anterior!='3'&&anterior!='4'){
                        Mapa[pac[0]][pac[1]-1]='P';
                        pac[1]--;
                        if(anterior=='.'||anterior=='*') Comidas--;
                    }
                    else{
                        Vidas--;
                        Mapa[pac[0]][pac[1]]=' ';
                        pac[0]=PN;
                        pac[1]=PM;
                        Mapa[PN][PM]='P';
                    }
                }
            }
        }
        comer=Pacman.comer;
        return anterior;
    }
    
    protected synchronized char moverPacB(int [] pac,int s, char anterior){
        //ARRIBA
        dir=s;
        if(s==1){
            if(pac[0]==0){
                if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                else Mapa[pac[0]][pac[1]]=' ';
                anterior=Mapa[n-1][pac[1]];
                Mapa[n-1][pac[1]]='P';
                pac[0]=n-1;
            }else{
                if((pac[0]-1>=0) && (Mapa[pac[0]-1][pac[1]]!='#')){
                    if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                    else Mapa[pac[0]][pac[1]]=' ';
                    anterior=Mapa[pac[0]-1][pac[1]];
                    Mapa[pac[0]-1][pac[1]]='P';
                    pac[0]--;
                    if(anterior=='.'||anterior=='*') Comidas--;
                    if(anterior=='1'){
                        Mapa[G[0].Ghost[0]][G[0].Ghost[1]]=' ';
                        G[0].Ghost[0]=G[0].GPN;
                        G[0].Ghost[1]=G[0].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='1';
                    }
                    if(anterior=='2'){
                        Mapa[G[1].Ghost[0]][G[1].Ghost[1]]=' ';
                        G[1].Ghost[0]=G[1].GPN;
                        G[1].Ghost[1]=G[1].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='2';
                    }
                    if(anterior=='3'){
                        Mapa[G[2].Ghost[0]][G[2].Ghost[1]]=' ';
                        G[2].Ghost[0]=G[2].GPN;
                        G[2].Ghost[1]=G[2].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='3';
                    }
                    if(anterior=='4'){
                        Mapa[G[3].Ghost[0]][G[3].Ghost[1]]=' ';
                        G[3].Ghost[0]=G[3].GPN;
                        G[3].Ghost[1]=G[3].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='4';
                    }
                }
            }
        }
        //DERECHA
        if(s==2){
            if(pac[1]==m-1){
                if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                else Mapa[pac[0]][pac[1]]=' ';
                anterior=Mapa[pac[0]][0];
                Mapa[pac[0]][0]='P';
                pac[1]=0;
            }else{
                if((pac[1]+1<m) && (Mapa[pac[0]][pac[1]+1]!='#')){
                    if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                    else Mapa[pac[0]][pac[1]]=' ';
                    anterior=Mapa[pac[0]][pac[1]+1];
                    Mapa[pac[0]][pac[1]+1]='P';
                    pac[1]++;
                    if(anterior=='.'||anterior=='*') Comidas--;
                    if(anterior=='1'){
                        Mapa[G[0].Ghost[0]][G[0].Ghost[1]]=' ';
                        G[0].Ghost[0]=G[0].GPN;
                        G[0].Ghost[1]=G[0].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='1';
                    }
                    if(anterior=='2'){
                        Mapa[G[1].Ghost[0]][G[1].Ghost[1]]=' ';
                        G[1].Ghost[0]=G[1].GPN;
                        G[1].Ghost[1]=G[1].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='2';
                    }
                    if(anterior=='3'){
                        Mapa[G[2].Ghost[0]][G[2].Ghost[1]]=' ';
                        G[2].Ghost[0]=G[2].GPN;
                        G[2].Ghost[1]=G[2].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='3';
                    }
                    if(anterior=='4'){
                        Mapa[G[3].Ghost[0]][G[3].Ghost[1]]=' ';
                        G[3].Ghost[0]=G[3].GPN;
                        G[3].Ghost[1]=G[3].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='4';
                    }
                }
            }
        }
        //ABAJO
        if(s==3){
            if(pac[0]==n-1){
                if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                else Mapa[pac[0]][pac[1]]=' ';
                anterior=Mapa[0][pac[1]];
                Mapa[0][pac[1]]='P';
                pac[0]=0;
            }else{
                if((pac[0]+1<n) && (Mapa[pac[0]+1][pac[1]]!='#')){
                    if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                    else Mapa[pac[0]][pac[1]]=' ';
                    anterior=Mapa[pac[0]+1][pac[1]];
                    Mapa[pac[0]+1][pac[1]]='P';
                    pac[0]++;
                    if(anterior=='.'||anterior=='*') Comidas--;
                    if(anterior=='1'){
                        Mapa[G[0].Ghost[0]][G[0].Ghost[1]]=' ';
                        G[0].Ghost[0]=G[0].GPN;
                        G[0].Ghost[1]=G[0].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='1';
                    }
                    if(anterior=='2'){
                        Mapa[G[1].Ghost[0]][G[1].Ghost[1]]=' ';
                        G[1].Ghost[0]=G[1].GPN;
                        G[1].Ghost[1]=G[1].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='2';
                    }
                    if(anterior=='3'){
                        Mapa[G[2].Ghost[0]][G[2].Ghost[1]]=' ';
                        G[2].Ghost[0]=G[2].GPN;
                        G[2].Ghost[1]=G[2].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='3';
                    }
                    if(anterior=='4'){
                        Mapa[G[3].Ghost[0]][G[3].Ghost[1]]=' ';
                        G[3].Ghost[0]=G[3].GPN;
                        G[3].Ghost[1]=G[3].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='4';
                    }
                }
            }
        }
        //IZQUIERDA
        if(s==4){
            if(pac[1]==0){
                if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                else Mapa[pac[0]][pac[1]]=' ';
                anterior=Mapa[pac[0]][m-1];
                Mapa[pac[0]][m-1]='P';
                pac[1]=m-1;
            }else{
                if((pac[1]-1>=0) && (Mapa[pac[0]][pac[1]-1]!='#')){
                    if(anterior=='0') Mapa[pac[0]][pac[1]]=anterior;
                    else Mapa[pac[0]][pac[1]]=' ';
                    anterior=Mapa[pac[0]][pac[1]-1];
                    Mapa[pac[0]][pac[1]-1]='P';
                    pac[1]--;
                    if(anterior=='.'||anterior=='*') Comidas--;
                    if(anterior=='1'){
                        Mapa[G[0].Ghost[0]][G[0].Ghost[1]]=' ';
                        G[0].Ghost[0]=G[0].GPN;
                        G[0].Ghost[1]=G[0].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='1';
                    }
                    if(anterior=='2'){
                        Mapa[G[1].Ghost[0]][G[1].Ghost[1]]=' ';
                        G[1].Ghost[0]=G[1].GPN;
                        G[1].Ghost[1]=G[1].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='2';
                    }
                    if(anterior=='3'){
                        Mapa[G[2].Ghost[0]][G[2].Ghost[1]]=' ';
                        G[2].Ghost[0]=G[2].GPN;
                        G[2].Ghost[1]=G[2].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='3';
                    }
                    if(anterior=='4'){
                        Mapa[G[3].Ghost[0]][G[3].Ghost[1]]=' ';
                        G[3].Ghost[0]=G[3].GPN;
                        G[3].Ghost[1]=G[3].GPM;
                        Mapa[G[0].GPN][G[0].GPM]='4';
                    }
                }
            }
        }
        comer=Pacman.comer;
        return anterior;
    }
    
    public void run(){
        while(Vidas>0&&Comidas>0){
            try {
                sleep(100);
                if(Vidas==0){
                    G[0].stop();
                    G[1].stop();
                    G[2].stop();
                    G[3].stop();
                    Pacman.stop();
                    uniqueInstance=null;
                    Mesa1.Tablero.dispose();
                    Menu=new IMenu(1);
                    Menu.setVisible(true);
                    Menu.setLocationRelativeTo(null);
                }
                if(Comidas==0){
                    G[0].stop();
                    G[1].stop();
                    G[2].stop();
                    G[3].stop();
                    Pacman.stop();
                    uniqueInstance=null;
                    Mesa1.Tablero.dispose();
                    Menu=new IMenu(2);
                    Menu.setVisible(true);
                    Menu.setLocationRelativeTo(null);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
