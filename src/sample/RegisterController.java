package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        EmailText.clear();
        TelephoneText.clear();
        AgeText.clear();
        AddressText.clear();
        PasswordText.clear();
        ConfirmPassText.clear();
        Mradio.setSelected(false);
        Fradio.setSelected(false);
        DOB.setValue(null);
        UserNameText.clear();
    }

    public void ContReg() {
        // e for empty
        char Gender = 'e';
        if ( Mradio.isSelected() )
            Gender = 'M';
        else if( Fradio.isSelected() )
            Gender = 'F';

        if(
                FirstNameText.getText().isEmpty() || LastNameText.getText().isEmpty() ||  EmailText.getText().isEmpty() ||
                        PasswordText.getText().isEmpty() || ConfirmPassText.getText().isEmpty() || UserNameText.getText().isEmpty() ||
                        AgeText.getText().isEmpty() || DOB.getValue() == null || Gender == 'e'
        ) {
            new Alert(Alert.AlertType.ERROR, "Please Check you have filled the required* Fields").showAndWait();
        }

    }
}
