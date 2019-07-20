package sample;

/**
 * Created by mittal3176 on 4/18/2017.
 */
public class Quarry extends Farm implements Farms {
    public Quarry (int posX, int posY) {
        super(posX, posY);
    }
    public int[] produce () {
        int[] resources = {0, 0, super.getProduction(), 0, 0};
        return resources;
    }
    public void levelUp() {
        super.levelUp();
    }
    public String getType() {
        return "Quarry";
    }
    public int[] getCost() {
        int[] cost = new int[5];
        switch (super.getLevel() + 1) {
            case 1: cost[0] = 5; cost[4] = 20; break;
            case 2: cost[0] = 20; cost[4] = 50; break;
            case 3: cost[0] = 40; cost[4] = 120; break;
            case 4: cost[0] = 80; cost[4] = 250; break;
            case 5: cost[0] = 160; cost[4] = 500; break;
            case 6: cost[0] = 320; cost[4] = 1000; break;
            case 7: cost[0] = 640; cost[4] = 2000; break;
        }
        return cost;
    }
    public static int[] getInitialCost() {
        int[] cost = new int[5];
        cost[0] = 5; cost[4] = 20;
        return cost;
    }

    public int getLevel() {
        return super.getLevel();
    }

    public String getPath() {
        return "resources/quarry 4";
    }

    @Override
    public String getProductionStats(){
        return "This is a quarry and it provides you with stone. Some upgrades \nrequire stone. This produces " + getProduction() + " stone per second.";
    }
}
