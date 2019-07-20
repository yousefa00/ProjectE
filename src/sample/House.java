package sample;

public class House implements Building {

    /* This class will represent houses that the user can build. It will
    ** randomly generate a number that will represent the capacity. The 
    ** method getCapacity() will return the max capacity. There will also be 
    ** an instance field representative of the current number of people in the                  // CREATE HIGH SCORE POPULATION
    ** house (alive). Method kill will check if the house has a population 
    ** greater than the number that needs to be removed, and if so, it will
    ** remove those people. Otherwise, it will set the number of people alive
    ** to zero. 
     */

    private int capacity;
    private int alive;
    private int level;

    public House() {
        capacity = (int) (Math.random() * 3) + 4;  // Randomly generated number between 4 and 6 which represents the number of people in the house
        alive = capacity;
        level = 1;
    }

    public int[] getCost() {
        int[] cost = new int[5];
        switch (level + 1) {
            case 1: cost[0] = 5; break;
            case 2: cost[0] = 10; cost[2] = 5; cost[4] = 10; break;
            case 3: cost[0] = 15; cost[2] = 10; cost[4] = 20; break;        // This switch statement determines the production rate based
            case 4: cost[0] = 20; cost[2] = 20; cost[4] = 50; break;        // on the level
            case 5: cost[0] = 30; cost[2] = 50; cost[4] = 200; break;
            case 6: cost[0] = 50; cost[2] = 100; cost[4] = 500; break;
            case 7: cost[0] = 100; cost[2] = 250; cost[4] = 1500; break;
        }
        return cost;
    }

    public void levelUp () {
        if(level <= 6)
            level++;
        switch (level) {
            case 1: capacity *= 1.5; break;
            case 2: capacity *= 1.5; break;
            case 3: capacity *= 1.5; break;        // This switch statement determines the production rate based
            case 4: capacity *= 1.75; break;        // on the level
            case 5: capacity *= 1.75; break;
            case 6: capacity *= 1.75; break;
            case 7: capacity *= 2; break;
        }
        alive = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getAlive() {
        return alive;
    }

    public double kill(int x) {   // Parameter x represents the number of people to kill
        if (alive >= x) {
            alive -= x;
            return x;
        } else {
            alive = 0;
            return alive;
        }
    }

    public static int[] getInitialCost() {
        int[] cost = new int[5];
        cost[0] = 5;
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public String getPath() {
        return "resources/house 4";
    }

    public String getProductionStats(){
        return "These houses are homes for your population. Build more to \nincrease your population and eventually reach the endgame!";
    }

    public String getType(){
        return "House";
    }
}
