package pacman;
import Vistas.*;

public class Pacman {
    public static void main(String[] args) {
        IMenu Menu = new IMenu(0);
        Menu.setVisible(true);
        Menu.setLocationRelativeTo(null);
    }
}
