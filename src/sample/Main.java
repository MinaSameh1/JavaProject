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
        // Note Nabil did the login page in FX and drew the login page, so credit goes to him on the login page
        // however we decided to think about it and remade the login page in SceneBuilder instead of writing it in code as it looked better that way.
        VBox root = new VBox();
        Pane pane = FXMLLoader.load(getClass().getResource("../Resources/Login.fxml"));
        root.getChildren().add(pane);
        primaryStage.setTitle("Clinic Manager Login");
        primaryStage.setScene(new Scene(root));
        stage = primaryStage;
        primaryStage.show();
        MainProgram.setLoginStage(primaryStage);
    }


    // main
    public static void main(String[] args) { launch(args); }
}
