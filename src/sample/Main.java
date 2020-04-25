package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Note Nabil did the inspiration and drew the login page, so credit goes to him on the login page
        VBox root = new VBox();
        Pane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.getChildren().add(pane);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
