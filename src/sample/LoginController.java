package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class LoginController {
    @FXML
    private Button butLog;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    public Label labelWelcome;
    @FXML
    private MenuItem LogMenuClose;
    private Stage stage;

    public void handleLog(){
        if(UserName.getText().isEmpty() || Password.getText().isEmpty() ){
            labelWelcome.setText("Please Enter Username and Password!");
            return;
        }
        SQLHelper helper = new SQLHelper();
        try {
            helper.Init();
            if(helper.HandleLogin(UserName.getText(),Password.getText())){
                System.out.println("WELCOME " + UserName.getText());
                labelWelcome.setText("WELCOME USER:" + UserName.getText() );
            }
            else {
                System.out.println("FAILED");
                labelWelcome.setText("Wrong Username or password!");
            }
        } catch (Exception e){
            System.out.println("FAILED " + e);
        }
        finally{
            helper.die();
        }
    }

    public void openRegister(){

        SQLHelper obj = new SQLHelper();

        try {
            Main.MainProgram.startReg();
            Main.MainProgram.hideLogin();
        } catch (Exception e) {
            e.printStackTrace();
            labelWelcome.setText(e.getMessage());
        }
        obj.die();
    }

    public void LogClose(){
        Main.MainProgram.closeLogin();
    }

    public void openAbout(){
        Main.MainProgram.showAbout();
    }



}
