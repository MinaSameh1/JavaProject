package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class RegisterController {
    // IDed in FXML File
    @FXML
    TextField FirstNameText,
            LastNameText,
            UserNameText,
            EmailText,
            TelephoneText,
            AgeText,
            AddressText;
    @FXML
    DatePicker DOB;
    @FXML
    RadioButton Mradio,Fradio;
    @FXML
    PasswordField PasswordText,ConfirmPassText;

    public void ResetReg(){
        FirstNameText.clear();
        LastNameText.clear();
        UserNameText.clear();
        EmailText.clear();
        TelephoneText.clear();
        AgeText.clear();
        AddressText.clear();
        PasswordText.clear();
        ConfirmPassText.clear();
        Mradio.setSelected(false);
        Fradio.setSelected(false);
        DOB.setValue(null);
    }

    public void ContReg() {
        // e for empty
        char Gender = 'e';
        if ( Mradio.isSelected() )
            Gender = 'M';
        else if( Fradio.isSelected() )
            Gender = 'F';

        // Make sure all fields are correctly filled
        if (
                FirstNameText.getText().isEmpty() || LastNameText.getText().isEmpty() ||  EmailText.getText().isEmpty() ||
                        PasswordText.getText().isEmpty() || ConfirmPassText.getText().isEmpty() || UserNameText.getText().isEmpty() ||
                        AgeText.getText().isEmpty() || DOB.getValue() == null || Gender == 'e'
        ) {
            new Alert(Alert.AlertType.ERROR, "Please Check you have filled the required* Fields").showAndWait();
            return;
        }
        SQLHelper Helper = new SQLHelper();
        try {
            Helper.Init();
            // This should return true if another user exists, if so prompt the user that name is already in use
            if( Helper.findByUserName(UserNameText.getText()).next() ) {
                new Alert(Alert.AlertType.ERROR, "Username already exists! Please choose another username").showAndWait();
            }
            if( UserNameText.getText().length() < 4 || UserNameText.getText().length() > 16 ){
                new Alert(Alert.AlertType.ERROR, "Please check that username is atleast 4 charecters long and at most 16 charecters ").showAndWait();

            }
            // if something wrong happens show error
        } catch (Exception e ){
            new Alert(Alert.AlertType.ERROR, "DB Error! " + e.getMessage()).showAndWait();
        } finally {
            // close connection
            Helper.die();
        }


    }

    public void goLogin(){
       Main.MainProgram.showLogin();
       Main.MainProgram.hideReg();
    }

    public void closeReg(){
        Main.MainProgram.closeReg();
        Main.MainProgram.showLogin();
    }

    public void exitReg(){
        Main.MainProgram.DIE();
    }

    public void openHelp(){
        Main.MainProgram.showHelp("HELPREG");
    }

    public void showAbout(){
        Main.MainProgram.showAbout();
    }
}
