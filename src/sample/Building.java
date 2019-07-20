package sample;

/**
 * Created by Student on 5/7/2017.
 */
public interface Building {
    int[] getCost();  // Returns the cost of an upgrade or an initial cost
    void levelUp();  // Increases the level by 1
    int getLevel();  // Returns the level
    String getPath();  // Gets the string path of the image
    String getProductionStats();  // Returns information about the building
    String getType();  // Returns the type of building
}
