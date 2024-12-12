package battleships;

import battleships.ships.Aeroplane;
import battleships.ships.Battleship;
import battleships.ships.TemplateShip;

public class ShipDemo {

    public static void main(String args[]) {
        Ship s = new Aeroplane();

        System.out.println("hello");
        s.rotate();
        System.out.println(s);
    }
}
