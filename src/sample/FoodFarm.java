package sample;

/**
 * Created by Raja on 5/13/2017.
 */
public class FoodFarm  extends Farm implements Farms {
    public FoodFarm(int posX, int posY) {
        super(posX, posY);
    }

    public void levelUp(){
        super.levelUp();
    };
    public int[] produce(){

        int[] resources = {0, 0, 0, super.getProduction(), 0};
        return resources;
    };
    public String getType() {
        return "Food Farm";
    }
    public int[] getCost() {
        int[] cost = new int[5];
        switch (super.getLevel() + 1) {
            case 1: cost[0] = 10; cost[4] = 10; break;
            case 2: cost[0] = 20; cost[2] = 2; cost[4] = 20; break;
            case 3: cost[0] = 40; cost[2] = 5; cost[4] = 50; break;
            case 4: cost[0] = 80; cost[2] = 20; cost[4] = 100; break;
            case 5: cost[0] = 160; cost[2] = 50; cost[4] = 300; break;
            case 6: cost[0] = 320; cost[2] = 150; cost[4] = 800; break;
            case 7: cost[0] = 640; cost[2] = 400; cost[4] = 1600; break;
        }
        return cost;
    }

    public static int[] getInitialCost() {
        int[] cost = new int[5];
        cost[0] = 10; cost[4] = 10;
        return cost;
    }

    @Override
    public String getProductionStats(){
        return "This is a food farm and it provides you with food. Your population \nneeds food to survive. This produces " + getProduction() + " food per second.";
    }

    public int getLevel() {
        return super.getLevel();
    }

    public String getPath() {
        return "resources/food 4";
    }
}
