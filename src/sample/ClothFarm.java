package sample;

/**
 * Created by mittal3176 on 4/18/2017.
 */
public class ClothFarm extends Farm implements Farms {
    public ClothFarm (int posX, int posY) {
        super(posX, posY);
    }

    public int[] produce () {
        int[] resources = {0, super.getProduction(), 0, 0, 0};
        return resources;
    }

    public void levelUp() {
        super.levelUp();
    }

    public String getType() {
        return "Cloth Farm";
    }
    public int[] getCost() {
        int[] cost = new int[5];
        switch (super.getLevel() + 1) {
            case 1: cost[0] = 10; cost[4] = 10; cost[2] = 2; break;
            case 2: cost[0] = 20; cost[4] = 25; cost[2] = 5; break;
            case 3: cost[0] = 40; cost[4] = 75; cost[2] = 10; break;
            case 4: cost[0] = 80; cost[4] = 250; cost[2] = 25; break;
            case 5: cost[0] = 160; cost[4] = 500; cost[2] = 50; break;
            case 6: cost[0] = 320; cost[4] = 1000; cost[2] = 100; break;
            case 7: cost[0] = 640; cost[4] = 2000; cost[2] = 300;  break;
        }
        return cost;
    }

    public static int[] getInitialCost() {
        int[] cost = new int[5];
        cost[0] = 10; cost[4] = 10; cost[2] = 2;
        return cost;
    }

    @Override
    public String getProductionStats() {
        return "This is a clothfarm and it provides you with cloth. Your population \nneeds cloth to survive. This produces " + getProduction() + " cloth per second.";
    }

    public int getLevel() {
        return super.getLevel();
    }

    public String getPath() {
        return "resources/cloth 4";
    }
}

