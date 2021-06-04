package Juego;

import Vistas.*;
import java.io.*;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Controlador {
    IMenu Menu;
    Mesa Mesa1;
    String Ruta;
    int Vidas;
    int comidas=0;
    int PN,PM;
    Map M = null;
    int m = 0,n = 0;
    Pac Pacman;
    Ghosts G1;
    Ghosts G2;
    Ghosts G3;
    Ghosts G4;
    
    public Controlador(String r, String v,IMenu M) throws IOException{
        Ruta=r;
        Vidas=Integer.parseInt(v);
        Menu=M;
        cargarMapa();
        Mesa1=new Mesa(Pacman);
        Jugar();
    }

    private void cargarMapa() throws FileNotFoundException, IOException{
        String texto;
        
        FileReader archivo = new FileReader(Ruta);
        BufferedReader contenido = new BufferedReader(archivo);
        //LEE FILAS
        if((texto=contenido.readLine())!=null){
            n=Integer.parseInt(texto);
        }
        //LEE COLUMNAS
        if((texto=contenido.readLine())!=null){
            m=Integer.parseInt(texto);
        }
        //CREA DIMENSIONES
        M = Map.instance(n,m);
        //LLENA EL MAPA Y POSICIONES DE PACMAN Y FANTASMAS
        int x=0;
        while((texto=contenido.readLine())!=null){
            char[] pal=texto.toCharArray();
            
            for(int i=0;i<m;++i){
                M.asignar(x, i, pal[i]);
                if(pal[i]=='.'){
                    comidas++;
                }
                if(pal[i]=='*'){
                    comidas++;
                }
                if(pal[i]=='P'){
                    Pacman=new Pac(x,i,Vidas);
                    PN=x;
                    PM=i;
                    M.cargarCoordenadas(x, i);
                }
                if(pal[i]=='1'){
                    G1=new Ghosts(x,i,'1');
                }
                if(pal[i]=='2'){
                    G2=new Ghosts(x,i,'2');
                }
                if(pal[i]=='3'){
                    G3=new Ghosts(x,i,'3');
                }
                if(pal[i]=='4'){
                    G4=new Ghosts(x,i,'4');
                }
            }
            ++x;
        }
        M.cargarComidas(comidas);
        M.cargarVidas(Vidas);
        M.cargarTodo(Pacman,G1,G2,G3,G4);
        Menu.dispose();
    }
    
    private void Jugar() throws IOException {
        M.instancias(Mesa1, Menu);
        M.start();
        G1.start();
        G2.start();
        G3.start();
        G4.start();
        Pacman.start();
        
    }
}
