package sample;

/**
 * Created by Raja on 5/16/2017.
 */
public class Shipyard implements Building {
    public int[] getCost(){
        return getInitialCost();
    }
    public static int[] getInitialCost() {
        int[] cost = {100, 0, 300, 0, 1000};
        return cost;
    }
    public void levelUp(){
    }
    public int getLevel(){
        return 0;
    }
    public String getPath(){
        return "resources/shipyard";
    }
    public static int[] getShipCost() {
        int[] cost = {250, 0, 50, 0, 1000};
        return cost;
    }
    @Override
    public String getProductionStats(){
        return "This is a shipyard. It is used to expand to other islands.";
    }
    public String getType(){
        return "Shipyard";
    }
}
