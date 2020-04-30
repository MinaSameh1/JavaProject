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

    // our event to handle login button click
    public void handleLog() throws Exception {
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
                User logged = helper.getUserPropByName(UserName.getText());
                // Open the correct form
              switch( logged.getUserType() ){
                    case SQLHelper.dbSchema.admin:
                        Main.MainProgram.startAdmin();
                        break;
                    /*case SQLHelper.dbSchema.doctor:
                        Main.MainProgram.startDoctor();
                        break;
                    case SQLHelper.dbSchema.doctorAssistant:
                        Main.MainProgram.startDoctorAssistant();
                        break;
                    case SQLHelper.dbSchema.cashier:
                        Main.MainProgram.startCashier();
                        break;
                    case SQLHelper.dbSchema.patient:
                        Main.MainProgram.startPatient();
                        break;*/
                }
                Main.MainProgram.hideLogin();
              UserName.clear();
              Password.clear();
            }
            else {
                System.out.println("FAILED WRONG PASS OR NAME" + UserName.getText() + "  " + Password.getText() );
                labelWelcome.setText("Wrong Username or password!");
            }
        } catch ( Exception e ){
            e.printStackTrace();
            System.out.println("FAILED " + e);
        }
        finally{
            helper.die();
        }
    }

    // Open Register Stage
    public void openRegister(){
        try {
            if(Main.MainProgram.startReg() )
                Main.MainProgram.hideLogin();
            else {
                System.err.println("FAILLLED to Open registration form!");
            }
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

    public void openAdmin(){
        Main.MainProgram.startAdmin();
    }

}
