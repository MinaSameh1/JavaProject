package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


public class MainController {

    // Login Page
    public Stage Login;
    // Registration Page
    public Stage Reg = null;
    // Admin page
    public Stage admin = null;
    // add new page
    public Stage addNew = null;
    // Doctor's page
    public Stage Doc = null;
    // Cashier's page
    public Stage Cashier = null;

    // empty constructor so if it gets called don't do anything *yet*
    public MainController(){ }

    // Close the program and all stages
    public void DIE(){
        if( Login != null ){
            Login.close();
        }
        if( Reg != null ){
            Reg.close();
        }
        if(admin != null){
            admin.close();
        }
        if( addNew != null ){
            addNew.close();
        }
        if( Doc != null ){
            Doc.close();
        }
        if( Cashier != null ){
            Cashier.close();
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

    // Start Login
    public boolean startLogin(){
        // Note Nabil did the login page in FX and drew the login page, so credit goes to him on the login page
        // however we decided to think about it and remade the login page in SceneBuilder instead of writing it in code as it looked better that way.

        // if login is already open show it, don't open a new one!
        if( Login != null){
            Main.MainProgram.Login.show();
            return true;
        }
        VBox root = new VBox();
        BorderPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("../Resources/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        root.getChildren().add(pane);
        Login.setTitle("Clinic Manager Login");
        Login.setScene(new Scene(root));
        Login.show();
        return true;
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
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "ERROR in " + e.getMessage()).showAndWait();
            return false;
        }
        return true;
    }

    public boolean startAdmin(){
        // if the Stage is already open, show it don't start
        if( admin != null){
            Main.MainProgram.showAdmin();
            return true;
        }
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("../Resources/Admin.fxml"));
            admin = new Stage();
            admin.setTitle("Clinic Manager Admin Panel");
            admin.setScene(new Scene(pane));
            admin.show();
        } catch ( Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "ERROR in " + e.getMessage()).showAndWait();
            return false;
        }
        return true;
    }

    public boolean startAddNew(){
        // if the Stage is already open, show it don't start
        if( addNew != null){
            Main.MainProgram.showAddNew();
            return true;
        }
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("../Resources/AddNew.fxml"));
            addNew = new Stage();
            addNew.setTitle("Clinic Manager add New User");
            addNew.setScene(new Scene(pane));
            // TODO: If it is closed, then reopen login in case it falls but i havn't got a good way of doing this yet so :dunno:
            /*addNew.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {

                }
            });*/
            addNew.show();
        } catch ( Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "ERROR in " + e.getMessage()).showAndWait();
            return false;
        }
        return true;
    }

    public boolean startDoctor(){
        // if the Stage is already open, show it don't start
        if( Doc != null){
            Main.MainProgram.showDoc();
            return true;
        }
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("../Resources/doctor.fxml"));
            Doc = new Stage();
            Doc.setTitle("Clinic Manager Doctor Panel");
            Doc.setScene(new Scene(pane));
            Doc.show();
        } catch ( Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "ERROR in " + e.getMessage()).showAndWait();
            return false;
        }
        return true;
    }

    public boolean startCashier(){
        // if the Stage is already open, show it don't start
        if( Cashier != null){
            Main.MainProgram.showCashier();
            return true;
        }
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("../Resources/cashier.fxml"));
            Cashier = new Stage();
            Cashier.setTitle("Clinic Manager Doctor Panel");
            Cashier.setScene(new Scene(pane));
            Cashier.show();
        } catch ( Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "ERROR in " + e.getMessage()).showAndWait();
            return false;
        }
        return true;
    }

    public void showReg(){
        if( Reg == null){
            System.err.println("ERROR Reg is null start it up first!");
            return;
        }
        Reg.show();
    }

    public void showAdmin(){
        if( admin == null){
            System.err.println("ERROR admin is null start it up first!");
            return;
        }
        admin.show();
    }

    public void showAddNew(){
        if( addNew == null){
            System.err.println("ERROR addNew is null start it up first!");
            return;
        }
        addNew.show();
    }

    public void showDoc(){
        if( Doc == null){
            System.err.println("ERROR Doc is null start it up first!");
            return;
        }
        Doc.show();
    }

    public void showCashier(){
        if( Cashier == null){
            System.err.println("ERROR Cashier is null start it up first!");
            return;
        }
        Doc.show();
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
    public void closeAdmin(){
        admin.close();
    }
    public void closeAddNew(){
        addNew.close();
    }
    public void closeDoc(){
        Doc.close();
    }
    public void closeCashier(){
        Cashier.close();
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
