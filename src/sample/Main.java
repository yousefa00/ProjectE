package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Project E");
        Scene scene = new Scene(root, 1030, 831);
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Baloo");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("RAJSEF = DREAM PROJECT TEAM");
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
