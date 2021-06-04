package Vistas;

import Juego.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mesa extends JPanel implements KeyListener{
    Map M;
    int m,n;
    Pac Pacman;
    public JFrame Tablero;
    
    public Mesa(Pac P){
        M=Map.getInstance();
        n=M.n;
        m=M.m;
        Pacman=P;
        
        Tablero = new JFrame("Pac-Man");
        Tablero.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tablero.addKeyListener(this);
        Tablero.setBackground(Color.BLACK);
        
        Bordes b = new Bordes();
        Tablero.add(b);
        Tablero.setSize(n*20,m*20+40);
        Tablero.setLocationRelativeTo(null);
        Tablero.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_S){
            Pacman.setSiguiente(3);
        }
        if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_W){
            Pacman.setSiguiente(1);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_D){
            Pacman.setSiguiente(2);
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_A){
            Pacman.setSiguiente(4);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
