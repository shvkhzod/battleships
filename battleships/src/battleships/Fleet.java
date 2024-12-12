package battleships;

import battleships.ships.*;

import java.util.ArrayList;
import java.util.List;
public class Fleet {

    private List<Ship> ships = new ArrayList<>();

    public Fleet(int battleships, int destroyers, int submarines, int aeroplanes, int aircraftCarriers) {

        for(int i = 0; i < battleships; i++) {
            this.ships.add(new Battleship());

        }

        for (int i = 0 ; i < destroyers; i++) {
            this.ships.add(new Destroyer());
        }

        for( int i = 0; i< submarines; i++) {
            this.ships.add(new Submarine());
        }

        for( int i = 0; i< aeroplanes; i++) {
            this.ships.add(new Aeroplane());
        }

        for( int i = 0; i< aircraftCarriers; i++) {
            this.ships.add(new AirCraftCarrier());
        }
    }

    public List<Ship> getShips() {return  this.ships;}
}
