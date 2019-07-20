package sample;

/**
 * Created by mittal3176 on 5/2/2017.
 */
public interface Farms extends Building {
    public int[] produce();
    public void levelUp();
    public int getPeople();
    public void addPeople(int p);
    public void removePeople(int p);
    public void resetPeople();
}
