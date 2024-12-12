package battleships.ships;

public class Aeroplane  extends TemplateShip{

    private static final int[][] pattern = new int[][] {
            new int[] {1,1,1},
            new int[] {0,1,0}
    };
    public Aeroplane() {
        super("aeroplane", "P", pattern.clone());
    }
}
