package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUIDesign.fxml"));
        primaryStage.setTitle("The Assignment Planner");
        primaryStage.setScene(new Scene(root, 641, 273));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {

      launch(args);
    }
}
