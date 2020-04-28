package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


public class MainController {

    // Login Page
    public Stage Login;
    // Registration Page
    public Stage Reg = null;

    public MainController() {}

    // Close the program and all stages
    public void DIE(){
        if( Login != null ){
            Login.close();
        }
        if( Reg != null ){
            Reg.close();
        }

    }

    public void setLoginStage(Stage stage){
        Login = stage;
    }
    public Stage getLoginStage(){
        return Login;
    }

    public void showLogin(){
        Login.show();
    }

    public void hideLogin(){
        Login.hide();
    }

    public boolean startReg(){
        // if the Stage is already open, show it don't start
        if( Reg != null){
            Main.MainProgram.showReg();
            return true;
        }
        try {
            // Note: Mostafa did the Register Form, he did a great job especially with choosing the picture
            BorderPane pane = FXMLLoader.load(getClass().getResource("../Resources/Register.fxml"));
            Reg = new Stage();
            Reg.setTitle("Clinic Manager Register");
            Reg.setScene(new Scene(pane));
            Reg.show();
        } catch ( Exception e){
            return false;
        }
        return true;
    }

    public Stage getRegStage(){
        if( Reg == null)
            return null;
        return Reg;
    }

    public void showReg(){
        if( Reg == null){
            System.err.println("ERROR Reg is null start it up first!");
            return;
        }
        Reg.show();
    }

    public void hideReg(){
        if( Reg == null){
            System.err.println("ERROR Reg is null start it up first!");
            return;
        }
        Reg.hide();
    }

    public void closeReg(){
        Reg.close();
    }
    public void closeLogin(){
        Login.close();
    }

    public void showAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setWidth(300);
        ScrollPane scroll = new ScrollPane();
        try{
            TextArea about = new TextArea(
                    new BufferedReader(
                            new InputStreamReader(
                                    getClass().getResourceAsStream("../Resources/ABOUT"))).lines()
                            .parallel().collect(Collectors.joining("\n"))
            );
            about.setEditable(false);
            scroll.setContent(about);
        } catch ( Exception e){
            System.err.println("COULDN'T FIND FILE ABOUT!");
            e.printStackTrace();
        }
        alert.getDialogPane().setContent(scroll);
        alert.showAndWait();
    }

    public void showHelp(String File){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setWidth(400);
        ScrollPane scroll = new ScrollPane();
        try{
            TextArea HelpReg = new TextArea(
                    new BufferedReader(
                            new InputStreamReader(
                                    getClass().getResourceAsStream("../Resources/" + File))).lines()
                            .parallel().collect(Collectors.joining("\n"))
            );
            HelpReg.setEditable(false);
            scroll.setContent(HelpReg);
        } catch ( Exception e){
            System.err.println("COULDN'T FIND FILE " + File + "! Please check your installation");
            e.printStackTrace();
        }
        alert.getDialogPane().setContent(scroll);
        alert.showAndWait();
    }
}
