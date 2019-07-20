package sample;

import java.util.ArrayList;

public class Island {

    /* This class represents the islands that the user will be able to expand to and
    ** build on. The island will have dimensions that are generated randomly with limits
    ** based on a parameter passed into the constructor.
     */

    private int xdimen, ydimen;
    private ArrayList<Farms> farms = new ArrayList();
    private Building[][] grid;
    private ArrayList<House> houses = new ArrayList();
    private boolean lock = true;

    public boolean isValid(int x, int y, String type, String farmType, boolean isAdding){
        switch (type) {
            case "Farm":
                if(isValidFarm(x, y, farmType, isAdding)) {
                    return true;
                }
            break;
            case "House":
                if (grid[x][y] == null) {
                    if (isAdding)
                        addHouse(x, y);
                    return true;
                }
            break;
            case "Extra":
                if(isValidFarm(x, y, farmType, isAdding)) {
                    return true;
                }
            break;
        }
        return false;
    }

    public boolean containsClass (Class claSS) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null &&  grid[i][j].getClass().equals(claSS)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Building getBuilding(int xPos, int yPos) {
        return grid[xPos][yPos];
    }

    public boolean isValidFarm(int x, int y, String type, boolean isAdding) {
        if (y != (ydimen - 1) && x != (xdimen - 1)) {
            if (grid[x][y] == null && grid[x + 1][y] == null && grid[x][y + 1] == null && grid[x + 1][y + 1] == null) {
                if (isAdding)
                    switch (type) {
                        case "LumberMill":
                            addFarm(x, y, 1);
                            break;
                        case "ClothFarm":
                            addFarm(x, y, 2);
                            break;
                        case "Quarry":
                            addFarm(x, y, 3);
                            break;
                        case "FoodFarm":
                            addFarm(x, y, 4);
                            break;
                        case "Shipyard":
                            addExtra(x, y);
                            break;
                    }
                return true;
            }
        }
        return false;
    }

    public Building[][] getGrid() {
        return grid;
    }

    public Island() {
        xdimen = 8;
        ydimen = 6;
        grid = new Building[xdimen][ydimen];
    }

    public int getXdimen() {
        return xdimen;
    }

    public int getYdimen() {
        return ydimen;
    }

    public void addExtra(int posX, int posY){
        Shipyard newYard = new Shipyard();
        grid[posX][posY] = newYard;
        grid[posX + 1][posY] = newYard;
        grid[posX][posY + 1] = newYard;
        grid[posX + 1][posY + 1] = newYard;
    }

    public void addFarm(int posX, int posY, int type) {
        Farms newFarm;
        switch (type) {
            case 1: newFarm = new LumberMill(posX, posY); break;
            case 2: newFarm = new ClothFarm(posX, posY); break;
            case 3: newFarm = new Quarry(posX, posY); break;
            case 4: newFarm = new FoodFarm(posX, posY); break;
            default: newFarm = new LumberMill(posX, posY);
        }
        addToGrid(newFarm, posX, posY);
    }

    public void addToGrid(Farms newFarm, int posX, int posY) {
        farms.add(newFarm);
        grid[posX][posY] = newFarm;
        grid[posX + 1][posY] = newFarm;
        grid[posX][posY + 1] = newFarm;
        grid[posX + 1][posY + 1] = newFarm;
    }

    public void addHouse(int posX, int posY) {
        House newHouse = new House();
        grid[posX][posY] = newHouse;
        houses.add(newHouse);
    }

    public double getPopulation() {
        double population = 0;
        for (House h: houses) {
            population += h.getAlive();
        }
        return population;
    }

    public int[] determineBottomRightCorner(int x, int y) {
        int[] pos = {x, y};
        if (y + 1 < grid[0].length && x + 1 < grid.length && grid[x][y].equals(grid[x + 1][y + 1])) {
            pos[0] = x + 1;
            pos[1] = y + 1;
        }
        else if (y + 1 < grid[0].length && grid[x][y].equals(grid[x][y+1])) {
            pos[0] = x;
            pos[1] = y + 1;
        }
        else if (x + 1 < grid.length && grid[x][y].equals(grid[x + 1][y])) {
            pos[0] = x + 1;
            pos[1] = y;
        }
        return pos;
    }

    public String getPath(int x, int y) {
        if (getBuilding(x, y) == null) {
            return "resources/square.jpg";
        } else {
            if (getBuilding(x, y).getPath().equals("resources/house 4")) {
                return getBuilding(x, y).getPath() + Integer.toString(getBuilding(x, y).getLevel()) + ".jpg";
            } else {
                if (getBuilding(x, y).getPath().equals("resources/shipyard")) {
                    return getBuilding(x, y).getPath() + " " + Integer.toString(determineSpotIn2by2(x, y, determineBottomRightCorner(x, y))) + ".jpeg";
                } else {
                    int[] bottomCorner;
                    bottomCorner = determineBottomRightCorner(x, y);
                    if (bottomCorner[0] == x && bottomCorner[1] == y) {
                        return getBuilding(x, y).getPath() + Integer.toString(getBuilding(x, y).getLevel()) + ".jpg";
                    } else {
                        return getBuilding(x, y).getPath().substring(0, getBuilding(x, y).getPath().length() - 1) + Integer.toString(determineSpotIn2by2(x, y, bottomCorner)) + ".jpeg";
                    }
                }
            }
        }
    }

    public int determineSpotIn2by2(int x, int y, int[] f) {
        if (x - f[0] == -1) {
            if (y - f[1] == -1) {
                return 1;
            } else {
                return 2;
            }
        } else if (y == f[1]) {
            return 4;
        } else {
            return 3;
        }
    }

    public ArrayList<Farms> getFarms(){
        return farms;
    }

    public int[] produce() {
        int[] resources = new int[5];
        for (int i = 0; i < resources.length; i++) {
            resources[i] = 0;
        }
        for (Farms x: farms) {
            for (int i = 0; i < resources.length; i++) {
                resources[i] += x.produce()[i];
            }
        }
        return resources;
    }

    public void optimizeResources() {
        int pop = (int) getPopulation();
        for (int i = 0; i < farms.size(); i++) {
            farms.get(i).resetPeople();
        }
        for (int i = 0; i < farms.size(); i++) {
            if (pop > 0) {
                farms.get(i).addPeople(1);
                pop--;
            } else {
                return;
            }
        }
        optimizeResourcesRecursive(farms, pop);
    }

    public void optimizeResourcesRecursive(ArrayList<Farms> farms, int pop) {
        if (pop > 0 && farms.size() > 0) {
            ArrayList<Farms> highLevelFarms = new ArrayList<Farms>();
            highLevelFarms.add(getHighestLevelFarm(new LumberMill(0,0), farms));
            highLevelFarms.add(getHighestLevelFarm(new ClothFarm(0,0), farms));
            highLevelFarms.add(getHighestLevelFarm(new FoodFarm(0,0), farms));
            highLevelFarms.add(getHighestLevelFarm(new Quarry(0,0), farms)); // Searches and identifies the highest level farm of each type
            sortLevels(highLevelFarms); // Sorts these farms in order from highest to lowest level
            int count = 0;
            while (count < 3) {
                for (int i = 0; i < highLevelFarms.size(); i++) {
                    if (pop > 0 && highLevelFarms.get(i) != null) {
                        highLevelFarms.get(i).addPeople(1); // Adds people to work in these farms
                        pop--;
                    }
                }
                count++;
            }
            if (pop > 0) {
                ArrayList<Farms> remainingFarms = new ArrayList<>();
                for (Farms farm: farms) {
                    remainingFarms.add(farm);
                }
                if (getHighestLevelFarm(new LumberMill(0,0), remainingFarms) != null)
                    remainingFarms.remove(getHighestLevelFarm(new LumberMill(0,0), remainingFarms));
                if (getHighestLevelFarm(new ClothFarm(0,0), remainingFarms) != null)
                    remainingFarms.remove(getHighestLevelFarm(new ClothFarm(0,0), remainingFarms));
                if (getHighestLevelFarm(new FoodFarm(0,0), remainingFarms) != null)
                    remainingFarms.remove(getHighestLevelFarm(new FoodFarm(0,0), remainingFarms));
                if (getHighestLevelFarm(new Quarry(0,0), remainingFarms) != null)
                    remainingFarms.remove(getHighestLevelFarm(new Quarry(0,0), remainingFarms));
                // Removes the highest level farms, and runs the function again with the remaining population and the rest of the farms
                optimizeResourcesRecursive(remainingFarms, pop);
            }
        }
    }
    
    public Farms getHighestLevelFarm(Farms c, ArrayList<Farms> farms) {
        Farms maxFarm = null;
        int maxLevel = 0;
        for (Farms farm: farms) {
            if (farm.getType().equals(c.getType())) {
                if (farm.getLevel() > maxLevel) {
                    maxLevel = farm.getLevel();
                    maxFarm = farm;
                }
            }
        }
        return maxFarm;
    }

    public void sortLevels(ArrayList<Farms> farms) {
        // This uses a modification of the selection sort in order to sort an ArrayList of farms in order from highest to lowest level.
        for (int i = 0; i < farms.size(); i++) {
            int maxLevel = 0;
            Farms maxFarm = null;
            for (int j = i; j < farms.size(); j++) {
                if (farms.get(j) != null && maxLevel < farms.get(j).getLevel()) {
                    maxLevel = farms.get(j).getLevel();
                    maxFarm = farms.get(j);
                }
            }
            farms.remove(maxFarm);
            farms.add(i, maxFarm);
        }
    }

    public void unlock(){
        lock = false;
    }

    public boolean isLocked(){
        return lock;
    }
}