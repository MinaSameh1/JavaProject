package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage stage;
    // The main controller we will use to control our Stages
    public static MainController MainProgram = new MainController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainProgram.setLoginStage(primaryStage);
        MainProgram.startLogin();
    }


    // main
    public static void main(String[] args) { launch(args); }
}
