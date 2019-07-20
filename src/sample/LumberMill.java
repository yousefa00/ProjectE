package sample;

public class LumberMill extends Farm implements Farms {
    public LumberMill(int posX, int posY) {
        super(posX, posY);
    }

    public void levelUp(){
        super.levelUp();
    };
    public int[] produce(){
        int[] resources = {super.getProduction(), 0, 0, 0, 0};
        return resources;
    };

    public String getType() {
        return "Lumbermill";
    }
    public int[] getCost() {
        int[] cost = new int[5];
        switch (super.getLevel() + 1) {
            case 1: cost[4] = 10; break;
            case 2: cost[4] = 20; cost[2] = 2; break;
            case 3: cost[4] = 80; cost[2] = 5; break;
            case 4: cost[4] = 240; cost[2] = 10; break;
            case 5: cost[4] = 480; cost[2] = 20; break;
            case 6: cost[4] = 1000; cost[2] = 40; break;
            case 7: cost[4] = 2000; cost[2] = 80; break;
            default:
        }
        return cost;
    }
    public static int[] getInitialCost() {
        int[] cost = new int[5];
        cost[4] = 10;
        return cost;
    }
    public int getLevel() {
        return super.getLevel();
    }

    public String getProductionStats() {
        return "This is a lumbermill and it provides you with wood. Upgrades \ntypically require wood. This produces " + getProduction() + " wood per second.";
    }

    public String getPath() {
        return "resources/lumbermill 4";
    }

}
