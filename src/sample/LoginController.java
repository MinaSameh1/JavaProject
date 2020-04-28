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
                System.out.println("FAILED WRONG PASS OR NAME" + UserName.getText() + "  " + Password.getText() );
                labelWelcome.setText("Wrong Username or password!");
            }
        } catch (Exception e){
            System.out.println("FAILED " + e);
        }
        finally{
            helper.die();
        }
    }

    // Open Register
    public void openRegister(){
        try {
            Main.MainProgram.startReg();
            Main.MainProgram.hideLogin();
        } catch (Exception e) {
            e.printStackTrace();
            labelWelcome.setText(e.getMessage());
        }
    }

    // Close login
    public void LogClose(){
        Main.MainProgram.closeLogin();
    }

    // open about menu
    public void openAbout(){
        Main.MainProgram.showAbout();
    }



}
