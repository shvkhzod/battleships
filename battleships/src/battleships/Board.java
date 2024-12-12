package battleships;

import javax.security.auth.login.FailedLoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final int height;
    private final int width;

    private final Square[][] board;

    private List<Ship> ships = new ArrayList<>();

    public Board(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.board = new Square[height][width];
        for (int i = 0; i < this.board.length; i++) {
            Square[] row = this.board[i];
            for (int j = 0; j < row.length; j++) {
                row[j] = new Square();
            }
        }

    }

    public void setup(Fleet fleet) {
        for (Ship s : fleet.getShips()) {
            placeShip(s);
        }
    }

    public void placeShip(Ship ship) {
        final int BREAK_THRESHOLD = 1000;

        Random random = new Random();

        //rotate the ship a random number of times
        final int rotations = random.nextInt(4);
        for (int i = 0; i < rotations; i++) {
            ship.rotate();
        }

        int breakCount = 0;
        boolean collision = true;
        while (collision) {

            if (breakCount >= BREAK_THRESHOLD) {
                //wipe the board

                for (int i = 0; i < this.board.length; i++) {
                    Square[] row = this.board[i];
                    for (int j = 0; j < row.length; j++) {
                        row[j].setShip(null);
                    }
                }

                //and reset and discard all ships
                for (Ship s : this.ships) {
                    s.setLocation(0, 0);

                }
                this.ships.clear();

                throw new FailedToPlaceException();
            }

            ship.rotate(); // try different orientation each time
            int x = random.nextInt(this.width - ship.getWidth());
            int y = random.nextInt(this.height - ship.getHeight());
            ship.setLocation(x, y);

            collision = false;

            for (final Ship s : this.ships) {
                if (s.overlap(ship)) {
                    collision = true;
                    break;// from the checking ship overlap loop
                }
            }

            if (!collision) {
                ship.addToBoard(this);
                this.ships.add(ship);
            }
            breakCount++;

        }
    }

    /**
     * Bombs the given square and returns an Outcome object that tells if any ship
     * was hit, and if so if it was sunk, and if so whether that ends the game or not.
     *
     * @param x
     * @param y
     * @return
     */
    public Outcome dropBomb(final int x, final int y) {
        Square square = getSquare(x, y);
        if (!square.isTried()) {
            square.setTried(true);
            Ship sunkShip = null;
            boolean gameWon = false;
            if (square.isHit()) {
                if (square.getShip().isSunk()) {
                    sunkShip = square.getShip();
                    gameWon = true;
                    for(Ship ship : this.ships) {
                        if (!ship.isSunk()) {
                            gameWon = false;
                            break;
                        }
                    }
                }
            }
            return new Outcome(x, y, square.isHit(), sunkShip, gameWon);
        } else {
            // this is a wasted turn - perhaps an exception would be a better idea?
            throw new IllegalStateException("Square already played!");
        }
    }


    public int getWidth() {return this.width;}
    public int getHeight() {return this.height;}

    public boolean inBounds(int x, int y) {
        return x >=0 && y >=0 && x <this.width && y<this.height;
    }

    public Square getSquare(int x, int y) {
        return this.board[y][x];
    }

    public String[] toStringArray(final boolean showShips) {
        final String[] array = new String[this.height];
        for(int y =0; y < this.height; y++) {
            final StringBuilder builder = new StringBuilder(this.width);
            for(int x=0; x < this.width; x++) {
                Square square = getSquare(x,y);
                builder.append(square.getCodeCharacter(showShips));
            }

            array[y] = builder.toString();
        }
        return array;
    }

    @Override
    public String toString(){
        final String[] array = toStringArray(true);
        final StringBuilder builder = new StringBuilder();
        for(int y = 0; y< this.height; y++) {
            builder.append(array[y]);
            builder.append("\n");
        }
        return builder.toString();
    }

}
