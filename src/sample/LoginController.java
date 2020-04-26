package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        labelWelcome.setText("HELLO!");
        SQLHelper obj = new SQLHelper();
        try {
            obj.Init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        obj.die();
    }

    public void LogClose(){
        Stage stage = (Stage) UserName.getScene().getWindow();
        stage.close();
    }
}
