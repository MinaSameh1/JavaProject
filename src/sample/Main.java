package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public Stage stage;
    // The main controller we will use to control our Stages
    public static MainController MainProgram = new MainController();

    @Override
    public void start(Stage primaryStage) throws Exception{

        MainProgram.setLoginStage(primaryStage);
        VBox root = new VBox();
        BorderPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../Resources/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().add(pane);
        primaryStage.setTitle("Clinic Manager Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    // main
    public static void main(String[] args) { launch(args); }
}
