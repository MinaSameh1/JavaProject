package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.transform.Result;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private Button butLog;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    private Label labelWelcome;

    public void handleLog(){
        if(UserName.getText().isEmpty() || Password.getText().isEmpty() ){
            labelWelcome.setText("ENTER TEXT PLS KTHXBAI");
            return;
        }
        SQLHelper helper = new SQLHelper();
        try {
            helper.Init();
            if(helper.HandleLogin(UserName.getText(),Password.getText())){
                System.out.println("WELCOME " + UserName.getText());
                labelWelcome.setText("WELCOME USER:" + UserName.getText());
            }
            else {
                System.out.println("FAILED");
                labelWelcome.setText("WRONG USERNAME OR PASSWORD! please check them");
            }
        } catch (Exception e){
            System.out.println("FAILED " + e);
        }
    }
}
