package battleships;

public class Square {

    private boolean isTried;
    private Ship ship;

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean isTried() {
        return isTried;
    }


    public boolean isHit() {
        return this.isTried && this.ship != null;
    }

    public boolean isMiss() {
        return this.isTried && this.ship == null;
    }
    public String getCodeCharacter(boolean showShips) {
        if(this.isTried) {
            if(this.isHit()) {
                return "*";
            } else if(this.isMiss()) {
                return "`";
            }
        } else {
            if(showShips && this.ship !=null) {
                return this.ship.getCode();
            }
        }

        return "~";
    }

    public void setTried(boolean tried) {
        this.isTried = tried;

        if(this.ship != null) {
            this.ship.incrementHitCount();
        }
    }
}
