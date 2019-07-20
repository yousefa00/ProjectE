package sample;


public class Farm implements Building {

    /* This class represents a farm which is a type of building that produces
    ** wood. There will be two instance fields that determine the level as
    ** well as the production rate. The method produce will return the amount
    ** of wood that should be produced per second. The method increaseLevel will
    ** increment the level and increase the production accordingly. The farm will also
    ** have two variables to store its position. These will be created in the constructor
     */
    
    private int people = 0;
    private int level = 1;
    private int production = 60;
    private int posX, posY;

    public Farm(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int[] getCost() {
        int[] cost = {10, 0, 0, 0, 0};
        return cost;
    }

    public String getPath() {
        return "resources/lumbermill";
    }

    public String getType(){
        return "1";
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        if (level <= 6)
            level++;   // Increments the level of the farm
        switch (level) {
            case 1: production = 60; break;
            case 2: production = 90; break;
            case 3: production = 150; break;        // This switch statement determines the production rate based
            case 4: production = 300; break;        // on the level
            case 5: production = 600; break;
            case 6: production = 1200; break;
            case 7: production = 2400; break;
        }
    }
    
    public void addPeople(int p) {
        people += p;
    }
    
    public void resetPeople() {
        people = 0;
    }
    
    public void removePeople(int p) {
        people -= p;
    }
    
    public int getPeople() {
        return people;
    }

    public int getProduction() {
        double multiplier = 0;
        if (getPeople() > 0) {
            multiplier = (Math.log(getPeople()) / Math.log(2)) / 2 + 1;
        }
        int resource = (int) (multiplier * production/60);
        return resource;
    }

    public String getProductionStats(){
        return "Farm";
    }
}
