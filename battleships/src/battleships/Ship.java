package battleships;

import java.awt.geom.Rectangle2D;

public abstract class Ship {
    private final String name;
    private final String code;

    protected int squareCount;
    private int hits;

    protected int x;
    protected int y;
    protected boolean horizontal;
    public Ship(String name, String code, int squareCount) {
        this.name = name;
        this.code = code;
        this.squareCount = squareCount;
    }



    public String getName() {return this.name;}
    public String getCode() {return this.code;}

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setLocation(int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public abstract void rotate();

    public abstract int getWidth();

    public abstract int getHeight();

    public boolean overlap(final Ship other) {
        final Rectangle2D rectThis = new Rectangle2D.Double(this.x, this.y, this.getWidth(), this.getHeight());
        final Rectangle2D rectOther = new Rectangle2D.Double(other.x, other.y, other.getWidth(), other.getHeight());
        final Rectangle2D intersection = rectThis.createIntersection((rectOther));
        return (intersection.getWidth() >=0) && (intersection.getHeight() >=0);

    }

    public boolean incrementHitCount() {
        if( this.hits < this.squareCount) {
            this.hits++;
        }
        return this.hits == this.squareCount;
    }

    public boolean isSunk() {
        return this.hits == this.squareCount;
    }

    public abstract void addToBoard(final Board board);


}

