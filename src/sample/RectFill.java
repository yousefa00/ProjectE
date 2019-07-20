package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;


/**
 * Created by ahmed1045 on 5/10/2017.
 */
public class RectFill {

    private Rectangle rectFill;
    private double capacity = 0;
    private double currentAmount = 0;

    public RectFill(Rectangle rectFill, double max, double current, String type){
        this.rectFill = rectFill;
        capacity = max;
        currentAmount = current;
        updateAmount(current, type);
        switch (type){
            case "people":
                setupPeopleRect();
                break;
            case "lumber":
                setupResRect(82);
                break;
            case "cloth":
                setupResRect(99);
                break;
            case "stone":
                setupResRect(116);
                break;
            case "food":
                setupResRect(133);
                break;
            case "gold":
                setupResRect(150);
                break;
        }
    }

    public void setupResRect(double layoutY){
        rectFill.setHeight(16.5);
        rectFill.setWidth(0);
        rectFill.setStroke(Color.BLACK);
        rectFill.setLayoutX(98);
        rectFill.setArcHeight(5);
        rectFill.setFill(Color.WHITE);
        rectFill.setLayoutY(layoutY);
        rectFill.setArcWidth(5);
    }

    public void setupPeopleRect(){
        rectFill.setHeight(27);
        rectFill.setStroke(Color.BLACK);
        rectFill.setLayoutX(250);
        rectFill.setArcHeight(5);
        rectFill.setLayoutY(17);
        rectFill.setArcWidth(5);
        rectFill.setWidth(0);
        rectFill.setFill(Color.WHITE);
    }

    public void updateAmount(double newAmount, String type){
        if (type.equals("people")){
            currentAmount = newAmount;
            if (currentAmount > capacity){
                currentAmount = capacity;
            }
            rectFill.setWidth(520 * (currentAmount / capacity));
        }
        else{
            currentAmount = newAmount;
            if (currentAmount > capacity){
                currentAmount = capacity;
            }
            rectFill.setWidth(46.5 * (currentAmount / capacity));
        }
    }

    public void updateMax(int newMax){
        capacity = newMax;
    }
}
