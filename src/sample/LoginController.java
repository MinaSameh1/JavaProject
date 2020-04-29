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

    // our event to handle button click
    public void handleLog(){
        // If the text fields are empty tell user
        if(UserName.getText().isEmpty() || Password.getText().isEmpty() ){
            labelWelcome.setText("Please Enter Username and Password!");
            return;
        }
        // if the values inputed don't make sense tell user
        if( UserName.getText().length() < 4 || UserName.getText().length() > 16 || Password.getText().length() < 4 || Password.getText().length() > 16 ){
            labelWelcome.setText("Please check your Username and password!");
            return;
        }
        // our SQL connector basically
        SQLHelper helper = new SQLHelper();
        try {
            // connect to DB
            helper.Init();
            // see if username and pass are correct
            if( helper.HandleLogin(UserName.getText(),Password.getText()) ){
                System.out.println("WELCOME " + UserName.getText());
                labelWelcome.setText("WELCOME USER:" + UserName.getText() );
            }
            else {
                System.out.println("FAILED WRONG PASS OR NAME" + UserName.getText() + "  " + Password.getText() );
                labelWelcome.setText("Wrong Username or password!");
            }
        } catch ( Exception e ){
            System.out.println("FAILED " + e);
        }
        finally{
            helper.die();
        }
    }

    // Open Register Stage
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
