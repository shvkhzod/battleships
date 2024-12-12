package battleships.ships;

public class AirCraftCarrier extends TemplateShip{

    private static final int[][] pattern = new int[][] {
            new int[] {1,1,1,1,0},
            new int[] {0,1,1,1,1}
    };
    public AirCraftCarrier() {
        super("aircraft carrier", "C", pattern.clone());
    }
}
