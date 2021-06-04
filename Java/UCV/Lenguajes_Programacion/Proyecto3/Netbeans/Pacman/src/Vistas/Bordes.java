package Vistas;

import Juego.Map;
import javax.swing.*;
import java.awt.*;

public class Bordes extends JPanel implements Runnable{
    char [][] Mapa;
    int m,n;
    Thread hilo;
    ImageIcon imagen;
    Map M;
    int cont=0, cont2=0;
    public Bordes(){
        M = Map.getInstance();
        Mapa = M.Mapa;
        n = M.n;
        m = M.m;
        hilo = new Thread(this);
        hilo.start();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.setBackground(Color.BLACK);
        
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                if(Mapa[i][j]=='#'){
                    g.setColor(Color.BLUE);
                    g.fillRect(j*20, i*20, 20, 20);
                }
                if(Mapa[i][j]=='*'){
                    imagen=new ImageIcon("src/res/powerBall.png");
                    g.drawImage(imagen.getImage(), j*20+5, i*20+5, 10, 10, null);
                }
                if(Mapa[i][j]=='.'){
                    imagen=new ImageIcon("src/res/food.png");
                    g.drawImage(imagen.getImage(), j*20+8, i*20+8, 2, 2, null);
                }
                if(Mapa[i][j]=='P'){
                    if(cont2%2==0){
                        if(M.dir==1) imagen=new ImageIcon("src/res/Pacman11.png");
                        if(M.dir==2) imagen=new ImageIcon("src/res/Pacman21.png");
                        if(M.dir==3) imagen=new ImageIcon("src/res/Pacman31.png");
                        if(M.dir==4) imagen=new ImageIcon("src/res/Pacman41.png");
                    }
                    if(cont2%2==1){
                        if(M.dir==1) imagen=new ImageIcon("src/res/Pacman12.png");
                        if(M.dir==2) imagen=new ImageIcon("src/res/Pacman22.png");
                        if(M.dir==3) imagen=new ImageIcon("src/res/Pacman32.png");
                        if(M.dir==4) imagen=new ImageIcon("src/res/Pacman42.png");
                    }
                    g.drawImage(imagen.getImage(), j*20+3, i*20+3, 14, 14, null);
                    cont++;
                    if(cont==10){
                        cont=0;
                        cont2++;
                        if (cont2==3) cont2=0;
                    }
                }
                if(Mapa[i][j]=='1'){
                    if(M.dir1==1) imagen=new ImageIcon("src/res/Ghost11.png");
                    if(M.dir1==2) imagen=new ImageIcon("src/res/Ghost12.png");
                    if(M.dir1==3) imagen=new ImageIcon("src/res/Ghost13.png");
                    if(M.dir1==4) imagen=new ImageIcon("src/res/Ghost14.png");
                    if(M.comer==1) imagen=new ImageIcon("src/res/Ghost.png");
                    g.drawImage(imagen.getImage(), j*20+4, i*20+4, 12, 12, null);
                }
                if(Mapa[i][j]=='2'){
                    if(M.dir2==1) imagen=new ImageIcon("src/res/Ghost21.png");
                    if(M.dir2==2) imagen=new ImageIcon("src/res/Ghost22.png");
                    if(M.dir2==3) imagen=new ImageIcon("src/res/Ghost23.png");
                    if(M.dir2==4) imagen=new ImageIcon("src/res/Ghost24.png");
                    if(M.comer==1) imagen=new ImageIcon("src/res/Ghost.png");
                    g.drawImage(imagen.getImage(), j*20+4, i*20+4, 12, 12, null);
                }
                if(Mapa[i][j]=='3'){
                    if(M.dir3==1) imagen=new ImageIcon("src/res/Ghost31.png");
                    if(M.dir3==2) imagen=new ImageIcon("src/res/Ghost32.png");
                    if(M.dir3==3) imagen=new ImageIcon("src/res/Ghost33.png");
                    if(M.dir3==4) imagen=new ImageIcon("src/res/Ghost34.png");
                    if(M.comer==1) imagen=new ImageIcon("src/res/Ghost.png");
                    g.drawImage(imagen.getImage(), j*20+4, i*20+4, 12, 12, null);
                }
                if(Mapa[i][j]=='4'){
                    if(M.dir4==1) imagen=new ImageIcon("src/res/Ghost41.png");
                    if(M.dir4==2) imagen=new ImageIcon("src/res/Ghost42.png");
                    if(M.dir4==3) imagen=new ImageIcon("src/res/Ghost43.png");
                    if(M.dir4==4) imagen=new ImageIcon("src/res/Ghost44.png");
                    if(M.comer==1) imagen=new ImageIcon("src/res/Ghost.png");
                    g.drawImage(imagen.getImage(), j*20+4, i*20+4, 12, 12, null);
                }
            }
        }
        
    }
    
    @Override
    public void run(){
        while(hilo.isAlive()){
            repaint();
            /*try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bordes.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }
}
