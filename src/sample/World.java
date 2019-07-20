package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class World {
    private ArrayList<Island> allIslands = new ArrayList<>();
    String[] changes = new String[5];
    private int[] resources = {80, 200, 200, 200, 0};  // Sets up initial amount of resources
    private int islandIndex = 0;  // Initial island index


    public World() {
        makeIslands();
    }

    public void makeIslands() {
        for (int i = 0; i < 3; i++) {
            allIslands.add(new Island());  // Constructs three islands
        }
        allIslands.get(0).unlock();  // Unlocks the first island
    }

    public void buy(int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            resources[i] -= cost[i];  // Substracts all of the resources by the cost of the item (Ex: [20, 0, 0, 20])
        }
    }

    public boolean canBuy(int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            if (resources[i] < cost[i]) {
                return false;                // Checks if all the resources required are greater than the cost
            }
        }
        return true;
    }

    public void addIsland(Island island, int x, int y) {
        for (int i = 10 * x; i < 10 * x + island.getXdimen(); i++) {
            for (int j = 10 * y; j < 10 * y + island.getYdimen(); j++) {
            }
        }
    }

    public boolean isFilled(int x, int y, int[][] fill) {
        int sum = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                sum += fill[i][j];
            }
        }
        return sum != 0;
    }

    public ArrayList<Island> getIslands(){
        return allIslands;
    }

    public Island getIsland() {
        return allIslands.get(islandIndex);
    }

    public void changeIslandIndex(int i) {
        if (i < 3) {
            islandIndex = i;           // Changes the index of the current island
        }
    }

    public void updateResources(){
        int[] oldResources = new int[5];  // Stores an array of the old resources before they are updated
        for (Island i: allIslands) {
            i.optimizeResources();
        }
        for (int i = 0; i < oldResources.length; i++){
            oldResources[i] = resources[i];
        }
        peopleUse();  // Subtracts the cloth and food that the population uses
        updateGold();  // Adds to the gold based on the number of citizens
        for (Island island : allIslands) {
            int[] res = island.produce();  // Gets an array of the island production of all resources
            for (int i = 0; i < res.length; i++) {
                resources[i] += res[i];             // Adds all of these resources to their respective positions in the resource array
                if (resources[i] > 6000 && i != (res.length - 1)){
                    resources[i] = 6000;        // If the value is greater than 6000 and the item is not gold, it will cap it at 6000
                }
                else if (resources[i] > 9999 && i == (res.length - 1)){
                    resources[i] = 9999;           // If it is gold, cap is 9999
                }
                if (resources[i] < 0) {
                    resources[i] = 0;
                }
            }
        }
        for (int i = 0; i < resources.length; i++){
            if (oldResources[i] < resources[i]){
                changes[i] = "+";
            }
            else if (oldResources[i] > resources[i]){       // Updates the array of "changes" to positive if the value is increasing...
                changes[i] = "-";
            }
            else{
                changes[i] = "0";
            }
        }
    }

    public int getTotalPopulation () {
        int pop = 0;
        for (int i = 0; i < allIslands.size(); i++) {
            pop += allIslands.get(i).getPopulation();       // Loops through all of the islands and increases the number of citizens
        }
        return pop;
    }

    public int[] getResources() {
        return resources;
    }

    public int getWood() {
        return resources[0];
    }

    public int getCloth() {
        return resources[1];
    }

    public int getStone() {
        return resources[2];
    }

    public int getPopulation(){
        return (int) allIslands.get(islandIndex).getPopulation();
    }

    public void peopleUse() {
        resources[1] -= getTotalPopulation()*2/3;       // Each citizen uses 2/3 cloth and food
        resources[3] -= getTotalPopulation()*2/3;
    }

    public String[] getChangeInResources(){
        return changes;
    }

    public void updateGold() {
        resources[4] += getTotalPopulation();
    }  // Each citizen produces 1 gold
}
