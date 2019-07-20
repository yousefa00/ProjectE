package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tooltip;
import java.net.URL;
import java.util.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class Controller implements Initializable{   

    private int xGrid = 0, yGrid = 0;
    private World world = new World();
    private Label[] resLabels = new Label[5];
    private Label testLabel = new Label();
    private Label infoLabel = new Label();
    private Label popLabel = new Label();
    private ArrayList<RectFill> allRectangles = new ArrayList<>();
    private boolean[] canBuyBuilding = {true, false, false, false, false};   // Sets up an array of boolean values which will determine which buildings cam be bought
    private boolean tutorialOver = false;
    private int choiceBoxIndex = 0;
    private ImageView rudy;
    private ImageView bubble;
    private ImageView lock;
    private ImageView[] arrows = new ImageView[5];
    private boolean canBuyShipyard = false;
    private ContextMenu menu = new ContextMenu();
    private boolean gameOver = false;

    @FXML
    private AnchorPane pane, islandPane;

    @FXML
    private Rectangle rect;

    @FXML
    private GridPane grid1, resGrid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ChoiceBox islandBox;

    private void makeGrid(final Island island){
        Building[][] grid = island.getGrid();                                       // This is a 2-dimensional array which houses instances of the building classes (farms and houses)
        final ImageView[][] images = new ImageView[grid.length][grid[0].length];        // Same thing but for images of those buildings
        menu = new ContextMenu();
        menu.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    event.consume();                                // Stops the event to check for another event
                }
            }
        });

        menu.setOnAction(new EventHandler<ActionEvent>() {          // This code will run when the user right-clicks on a square
            @Override
            public void handle(ActionEvent event) {
                switch (((MenuItem)event.getTarget()).getText()) {
                    case "Build LumberMill":
                        if (!tutorialOver){
                            testLabel.setVisible(false);
                            tutorialOver = true;
                            rudy.setVisible(false);             // This will remove many of the tutorial features
                            bubble.setVisible(false);
                        }
                        if (island.isValid(xGrid, yGrid, "Farm", "LumberMill",true)){
                            images[xGrid][yGrid].setImage(new Image("resources/lumbermill 1.jpeg"));
                            images[xGrid][yGrid + 1].setImage(new Image("resources/lumbermill 2.jpeg"));        // Adds all of the imageviews to their respective places (farms take up 4 squares)
                            images[xGrid + 1][yGrid].setImage(new Image("resources/lumbermill 3.jpeg"));
                            images[xGrid + 1][yGrid + 1].setImage(new Image("resources/lumbermill 41.jpg"));
                            world.buy(LumberMill.getInitialCost());     // Runs buy from the world class using static method, getInitialCost()
                        }
                        break;
                    case "Build ClothFarm":
                        if (island.isValid(xGrid, yGrid, "Farm", "ClothFarm",true)){
                            images[xGrid][yGrid].setImage(new Image("resources/cloth 1.jpeg"));
                            images[xGrid][yGrid + 1].setImage(new Image("resources/cloth 2.jpeg"));
                            images[xGrid + 1][yGrid].setImage(new Image("resources/cloth 3.jpeg"));
                            images[xGrid + 1][yGrid + 1].setImage(new Image("resources/cloth 41.jpg"));
                            world.buy(ClothFarm.getInitialCost());
                        }
                        break;
                    case "Build Quarry":
                        if (island.isValid(xGrid, yGrid, "Farm", "Quarry",true)){
                            images[xGrid][yGrid].setImage(new Image("resources/quarry 1.jpeg"));
                            images[xGrid][yGrid + 1].setImage(new Image("resources/quarry 2.jpeg"));
                            images[xGrid + 1][yGrid].setImage(new Image("resources/quarry 3.jpeg"));
                            images[xGrid + 1][yGrid + 1].setImage(new Image("resources/quarry 41.jpg"));
                            world.buy(Quarry.getInitialCost());
                        }
                        break;
                    case "Build House":
                        if (island.isValid(xGrid, yGrid, "House","NA", true)){
                            images[xGrid][yGrid].setImage(new Image("resources/house 41.jpg"));
                            world.buy(House.getInitialCost());
                        }
                        break;
                    case "Upgrade":
                        if (world.canBuy(island.getBuilding(xGrid, yGrid).getCost())) {  // Checks to see if the user has enough resources to buy the upgrade
                            world.buy(island.getBuilding(xGrid, yGrid).getCost());
                            island.getBuilding(xGrid, yGrid).levelUp();  // Levels up the building
                            int x = island.determineBottomRightCorner(xGrid, yGrid)[0];
                            int y = island.determineBottomRightCorner(xGrid, yGrid)[1];
                            images[x][y].setImage(new Image(grid[x][y].getPath() + island.getBuilding(xGrid,yGrid).getLevel() + ".jpg"));  // Changes the bottom right square based on the level of the building
                        }
                        break;
                    case "Build Food Farm":
                        if (island.isValid(xGrid, yGrid, "Farm", "FoodFarm",true)){
                            images[xGrid][yGrid].setImage(new Image("resources/food 1.jpeg"));
                            images[xGrid][yGrid + 1].setImage(new Image("resources/food 2.jpeg"));
                            images[xGrid + 1][yGrid].setImage(new Image("resources/food 3.jpeg"));
                            images[xGrid + 1][yGrid + 1].setImage(new Image("resources/food 41.jpg"));
                            world.buy(FoodFarm.getInitialCost());
                        }
                        break;
                    case "Build Shipyard":
                        if (island.isValid(xGrid, yGrid, "Farm", "Shipyard",true)){
                            images[xGrid][yGrid].setImage(new Image("resources/shipyard 1.jpeg"));
                            images[xGrid][yGrid + 1].setImage(new Image("resources/shipyard 2.jpeg"));
                            images[xGrid + 1][yGrid].setImage(new Image("resources/shipyard 3.jpeg"));
                            images[xGrid + 1][yGrid + 1].setImage(new Image("resources/shipyard 4.jpeg"));
                            world.buy(Shipyard.getInitialCost());
                        }
                        break;
                    case "Build Ship":
                        world.buy(Shipyard.getShipCost());
                        if (world.getIslands().get(1).isLocked()) {
                            world.getIslands().get(1).unlock();
                            infoLabel.setText("Youaja land unlocked! Using this island, I hope you can expand, \nincreasing your population and " +     // Unlocks the next island and creates a dialogue
                                    "your available resources. Remember, \nthe more people you have, the more gold you earn!");
                        }
                        else {
                            world.getIslands().get(2).unlock();
                            infoLabel.setText("McSwagger Island unlocked!");
                        }
                        rudy.setVisible(true); bubble.setVisible(true); infoLabel.setVisible(true);
                        break;
                }
                menu.getItems().clear();  // Removes all of the entries from the menuitem so that new ones can be added on the next click
            }
        });

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                images[i][j] = new ImageView();
                images[i][j].setFitHeight(80); images[i][j].setFitWidth(80);        // Array of imageviews that represent the land on the island
                String path = island.getPath(i, j);
                images[i][j].setImage(new Image(path));
                final int tempX = i; final int tempY = j;  // Declared final so that they can be modified in the event handler
                images[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.isSecondaryButtonDown() && !island.isLocked()) {   // Checks for a right click and ensures that the island is unlocked first
                            xGrid = tempX; yGrid = tempY;
                            menu.getItems().clear();        // Clears menuitems
                            if (island.isValid(xGrid, yGrid, "Farm", "NA",false)){   // Checks if the current square is a valid position for a new building (all 4 spaces are unoccupied)
                                if (canBuyBuilding[1] && world.canBuy(LumberMill.getInitialCost())) {
                                    MenuItem farmMenu = new MenuItem("Build LumberMill");           // Gives the user the option to buy the building
                                    menu.getItems().add(farmMenu);
                                }
                                if (canBuyBuilding[2] && world.canBuy(ClothFarm.getInitialCost())) {
                                    MenuItem farmMenu = new MenuItem("Build ClothFarm");
                                    menu.getItems().add(farmMenu);
                                }
                                if (canBuyBuilding[3] && world.canBuy(Quarry.getInitialCost())) {
                                    MenuItem farmMenu = new MenuItem("Build Quarry");
                                    menu.getItems().add(farmMenu);
                                }
                                if (canBuyBuilding[4] && world.canBuy(FoodFarm.getInitialCost())) {
                                    MenuItem farmMenu = new MenuItem("Build Food Farm");
                                    menu.getItems().add(farmMenu);
                                }
                                if (canBuyShipyard && world.canBuy(Shipyard.getInitialCost())) {
                                    MenuItem shipyardMenu = new MenuItem("Build Shipyard");
                                    menu.getItems().add(shipyardMenu);
                                }
                            }
                            if (island.isValid(xGrid, yGrid, "House", "NA",false)){
                                if (canBuyBuilding[0] && world.canBuy(House.getInitialCost())) {
                                    MenuItem houseMenu = new MenuItem("Build House");
                                    menu.getItems().add(houseMenu);
                                }
                            }
                            else {
                                if (world.getIsland().getBuilding(xGrid, yGrid).getType().equals("Shipyard")){
                                    if (world.canBuy(Shipyard.getShipCost())){
                                        MenuItem buildShip = new MenuItem("Build Ship");                // The shipyard doesnt have an upgrade, instead, it allows the user to build a ship
                                        menu.getItems().add(buildShip);
                                    }
                                }
                                else if(world.canBuy(island.getBuilding(xGrid, yGrid).getCost())) {
                                    if (island.getBuilding(xGrid, yGrid).getLevel() < 7) {
                                        MenuItem upgradeMenu = new MenuItem("Upgrade");
                                        menu.getItems().add(upgradeMenu);
                                    }
                                }
                            }
                            menu.show(islandPane, event.getScreenX(), event.getScreenY());   // Displays the menu at the current mouse position of the user
                            event.consume();
                        }
                        else if (event.isPrimaryButtonDown()){
                            xGrid = tempX; yGrid = tempY;
                            if (island.getBuilding(xGrid, yGrid) != null && tutorialOver){
                                getInfo(island, xGrid, yGrid);
                                menu.hide();                            // If the space that the user is clicking is not blank and the tutorial is over, this will display information about the building
                                event.consume();
                            }
                            else {
                                if (tutorialOver){
                                    rudy.setVisible(false); bubble.setVisible(false); infoLabel.setVisible(false);  // If there is no building, it will hide rudy and his text
                                }
                                menu.hide();
                                event.consume();
                            }
                        }
                    }
                });
                grid1.add(images[i][j], j , i);
            }
        }

    }

    public void getInfo(Island island, int xPos, int yPos){
        rudy.setVisible(true); bubble.setVisible(true); infoLabel.setVisible(true); testLabel.setVisible(false);
        Building tempBuilding = island.getBuilding(xPos, yPos);
        int level = tempBuilding.getLevel();
        int[] cost = tempBuilding.getCost();
        String totalCost = "";                                  // Retrieves some basic information about the building
        String info = tempBuilding.getProductionStats();
        String type = tempBuilding.getType();
        for (int i = 0; i < cost.length; i++){
            if (cost[i] != 0){
                totalCost += cost[i];
                switch (i){
                    case 0:
                        totalCost += " Wood,  ";
                        break;
                    case 1:
                        totalCost += " Cloth,  ";
                        break;
                    case 2:
                        totalCost += " Stone,  ";
                        break;
                    case 3:
                        totalCost += " Food,  ";
                        break;
                    case 4:
                        totalCost += " Gold,  ";
                        break;
                }
            }
        }
        if (totalCost.length() >= 3)
            totalCost = totalCost.substring(0, totalCost.length() - 3);
        infoLabel.setText("Building: " + type + "\nLevel: " + level + "\nUpgrade Cost: " + totalCost + "\nInfo: " + info);
    }

    public void tutorial() {
        pane.getChildren().add(testLabel);
        runWithDelay("Hey Chief! We're happy to have you here! This is your first island, \nhopefully the first of many!",500);
        buyHouse(5000);
    }

    @FXML
    private void updateFX() {
        world.updateResources();
        allRectangles.get(0).updateAmount(world.getTotalPopulation(), "people");
        popLabel.setText(String.valueOf((int) world.getTotalPopulation()));
        for (int i = 0; i < resLabels.length; i++) {
            resLabels[i].setText("   " + Integer.toString(world.getResources()[i]) + "   ");
            allRectangles.get(i + 1).updateAmount(world.getResources()[i], "farm");
        }
        world.changeIslandIndex(islandBox.getSelectionModel().getSelectedIndex());
        if (getCurrentIndexChoiceBox() != choiceBoxIndex) {
            menu.hide();
            makeGrid(world.getIsland());
            choiceBoxIndex = getCurrentIndexChoiceBox();
            if (world.getIsland().isLocked()){
                lock.setVisible(true);
            }
            else{
                lock.setVisible(false);
            }
        }
        if (world.getTotalPopulation() >= 20) {
            canBuyShipyard = true;
        } else {
            canBuyShipyard = false;
        }
        String[] changes = world.getChangeInResources();
        for (int i = 0; i < changes.length; i++){
            if (changes[i].equals("+")){
                arrows[i].setVisible(true);
                arrows[i].setImage(new Image("resources/up.png"));
            }
            else if (changes[i].equals("-")){
                arrows[i].setVisible(true);
                arrows[i].setImage(new Image("resources/down.png"));
            }
            else{
                arrows[i].setVisible(false);
            }
        }
        checkEndGame();
    }

    public void checkEndGame() {
        if (world.getTotalPopulation() > 1000 && !gameOver) {
            gameOver = true;
            rudy.setVisible(true); bubble.setVisible(true); infoLabel.setText(""); infoLabel.setVisible(true);
            String message = "Congratulations, you have won the game by reaching the required \npopulation of 1000!! If you'd like, you can keep playing.";
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run () {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            TextBox.runTextBox(message, infoLabel,0);
                            timer.cancel();
                        }
                    });
                }
            }, 0,100000);
        }
    }

    public int getCurrentIndexChoiceBox() {
        return islandBox.getSelectionModel().getSelectedIndex();
    }

    public void setText(Label label, String text) {
        label.setText(text);
    }

    public void runWithDelay(String message, int delay) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run () {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        TextBox.runTextBox(message, testLabel,0);
                        timer.cancel();
                    }
                });
            }
        }, delay,250);
    }

    public void buyHouse(int delay) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run () {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (world.getIslands().get(0).containsClass(House.class)) {
                            timer.cancel();
                            canBuyBuilding[0] = false;
                            canBuyBuilding[2] = true;
                            canBuyBuilding[4] = true;
                            buyClothFarm(0);
                        } else {
                            runWithDelay("Your first job will be to build houses for your citizens. Right click on the \nisland and click build house to get started.  You will see your population \nmeter start to fill up at the top of the screen.",0);
                        }
                    }
                });
            }
        }, delay,9000);
    }

    public void buyClothFarm(int delay) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run () {                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (world.getIslands().get(0).containsClass(ClothFarm.class) && world.getIslands().get(0).containsClass(FoodFarm.class)) {
                            canBuyBuilding[2] = false;
                            canBuyBuilding[4] = false;
                            canBuyBuilding[0] = true;
                            timer.cancel();
                            buyLumberMill(0);
                        } else {
                            runWithDelay("Your citizens need food and cloth to live! Only then will they be satisfied \nand pay high taxes. Right click on the island to build a cloth and food \nfarm.", 0);
                        }
                    }
                });
            }
        }, delay,7000);
    }

    public void buyLumberMill(int delay) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run () {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (world.getIslands().get(0).containsClass(LumberMill.class)) {
                            for (int i = 0; i < canBuyBuilding.length; i++) {
                                canBuyBuilding[i] = true;
                            }
                            timer.cancel();
                        } else {
                            if (world.getResources()[0] <= 40) {
                                canBuyBuilding[0] = false;
                                canBuyBuilding[1] = true;
                                runWithDelay("Looks like your wood resources are dwindling! Time to get a LumberMill.", 0);
                            } else {
                                runWithDelay("Your cloth and food farms are creating a surplus of resources for your \npopulation. You can afford to build more houses!",0);
                            }
                        }
                    }
                });
            }
        }, delay,6000);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setMinWidth(scrollPane.getWidth());
        pane.setMaxWidth(scrollPane.getWidth());

        resGrid.setGridLinesVisible(true);
        makeGrid(world.getIsland());
        resGrid.setPrefHeight(75); resGrid.setPrefWidth(100);
        resGrid.add(new Label("   Wood   "), 0, 0);
        resGrid.add(new Label("   Cloth   "), 0, 1);      // Adds three labels titled after resources to the gridpane
        resGrid.add(new Label("   Stone   "), 0, 2);
        resGrid.add(new Label("   Food   "), 0, 3);
        resGrid.add(new Label("   Gold   "), 0, 4);

        for (int i = 0; i < resLabels.length; i++){
            resLabels[i] = new Label("0");
            resGrid.add(resLabels[i], 1, i);
        }

        int layoutY = 87;
        for (int i = 0; i  < arrows.length; i++){
            arrows[i] = new ImageView();
            arrows[i].setLayoutX(148);
            arrows[i].setFitHeight(10);
            arrows[i].setFitWidth(10);
            arrows[i].setLayoutY(layoutY);
            pane.getChildren().add(arrows[i]);
            layoutY += 17;
        }

        Rectangle peopleFill = new Rectangle();
        Rectangle peopleFull = new Rectangle();
        allRectangles.add(new RectFill(peopleFill, 1000, 0, "people"));
        pane.getChildren().add(peopleFill);

        Rectangle lumberFill = new Rectangle();
        allRectangles.add(new RectFill(lumberFill, 6000, world.getResources()[0], "lumber"));
        pane.getChildren().add(1, lumberFill);

        Rectangle clothFill = new Rectangle();
        allRectangles.add(new RectFill(clothFill, 6000, 0, "cloth"));
        pane.getChildren().add(2, clothFill);

        Rectangle quarryFill = new Rectangle();
        allRectangles.add(new RectFill(quarryFill, 6000, 0, "stone"));
        pane.getChildren().add(3, quarryFill);

        Rectangle foodFill = new Rectangle();
        allRectangles.add(new RectFill(foodFill, 6000, 0, "food"));
        pane.getChildren().add(4, foodFill);

        Rectangle goldFill = new Rectangle();
        allRectangles.add(new RectFill(goldFill, 9999, 0, "gold"));
        pane.getChildren().add(5, goldFill);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Swashbuckler Island",
                        "Youaja Land",
                        "McSwagger Island"          // Creates an observablearraylist
                );
        islandBox.setItems(options);  // Initializes choicebox with array options
        islandBox.getSelectionModel().selectFirst();  // Sets first value in array as the initial value
        islandBox.setTooltip(new Tooltip("Choose an island"));  // Creates a message that appears when the mouse hovers over the choicebox
        islandBox.setLayoutX(850.0);
        islandBox.setLayoutY(20.0);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        updateFX();
                    }
                });
            }
        }, 0, 1000);
        islandPane.getChildren().add(0, new ImageView(new Image("resources/island FINAL2.jpg")));
        pane.getChildren().add(1, new ImageView(new Image("resources/Scroll.png")));

        pane.getChildren().add(peopleFull);
        peopleFull.setHeight(27);
        peopleFull.setStroke(Color.BLACK);
        peopleFull.setFill(Color.rgb(196, 196, 196, .5));
        peopleFull.setLayoutX(250);
        peopleFull.setArcHeight(5);
        peopleFull.setLayoutY(17);
        peopleFull.setArcWidth(5);
        peopleFull.setWidth(520);

        bubble = new ImageView(new Image("resources/bubble.png"));
        pane.getChildren().add(bubble);
        bubble.setFitHeight(150);
        bubble.setLayoutX(240);
        bubble.setLayoutY(654);

        testLabel.setLayoutX(335);
        testLabel.setLayoutY(669);
        testLabel.setStyle("-fx-font-family: Baloo; -fx-font-size: 15;");

        infoLabel.setVisible(false);
        infoLabel.setLayoutX(335);
        infoLabel.setLayoutY(669);
        infoLabel.setStyle("-fx-font-family: Baloo; -fx-font-size: 15;");
        pane.getChildren().add(pane.getChildren().size(), infoLabel);

        rudy = new ImageView(new Image("resources/Rudy.png"));
        pane.getChildren().add(rudy);
        rudy.setFitHeight(215.0);
        rudy.setFitWidth(250.0);
        rudy.setLayoutX(30.0);
        rudy.setLayoutY(614.0);
        pane.getChildren().add(popLabel);
        popLabel.setLayoutX(500);
        popLabel.setLayoutY(12);
        popLabel.setStyle("-fx-font-family: Baloo; -fx-font-size: 20;");
        tutorial();
        grid1.setLayoutX(425.0);
        grid1.setLayoutY(335.0);
        scrollPane.setVvalue(.7);
        scrollPane.setHvalue(.4);
        resGrid.setLayoutX(45);
        resGrid.setLayoutY(82);
        islandPane.setMinHeight(1262.0);
        islandPane.setMinWidth(1324.0);
        islandPane.setMaxHeight(1262.0);
        islandPane.setMaxWidth(1324.0);

     //   lock = new ImageView(new Image("resources/lock.png"));
//        pane.getChildren().add(lock);
//        lock.setFitWidth(100);
 //       lock.setFitHeight(110);
  //      lock.setLayoutX(442);
   //     lock.setLayoutY(310);
    //    lock.setVisible(false);

    }
}