package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LoginController {
    @FXML
    private Button butLog;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField Password;
    @FXML
    private Label labelWelcome;
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
                labelWelcome.setText("WELCOME USER:" + UserName.getText());
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

            // Note: Mostafa did the Register Form, he did a great job especially with choosing the picture
            BorderPane pane = FXMLLoader.load(getClass().getResource("../Resources/Register.fxml"));
            Stage Reg = new Stage();
            Reg.setTitle("Hello World");
            Reg.setScene(new Scene(pane));
            Reg.show();
            labelWelcome.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
            labelWelcome.setText(e.getMessage());
        }
        obj.die();
    }

    public void LogClose(){
        Stage stage = (Stage) UserName.getScene().getWindow();
        stage.close();
    }
}
