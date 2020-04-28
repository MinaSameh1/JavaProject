package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class RegisterController implements Initializable {
    // IDed in FXML File
    @FXML
    TextField FirstNameText,
            LastNameText,
            UserNameText,
            EmailText,
            TelephoneText,
            AltPhoneText,
            AgeText,
            AddressText;
    @FXML
    DatePicker DOB;
    @FXML
    RadioButton Mradio,Fradio;
    @FXML
    PasswordField PasswordText,
            ConfirmPassText;
    @FXML
    ComboBox BloodTypeBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add items to the comboBox
        BloodTypeBox.getItems().addAll("A","B","AB","O");

        // force the field to be numeric only
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            // Regex to check if the numbers
            if (newText.matches("^[0-9]+$")) {
                return change;
            }
            return null;
        };
        // make telephone,Altphone,Age only numeric
        AgeText.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        TelephoneText.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        AltPhoneText.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));


    }

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
        BloodTypeBox.valueProperty().set(null);
    }

    public void ContReg() {
        // e for empty
        char Gender = 'e';
        if ( Mradio.isSelected() )
            Gender = 'M';
        else if( Fradio.isSelected() )
            Gender = 'F';

        // Make sure all fields are correctly filled
        try {
            if (
                    FirstNameText.getText().isEmpty() || LastNameText.getText().isEmpty() || EmailText.getText().isEmpty() ||
                            PasswordText.getText().isEmpty() || ConfirmPassText.getText().isEmpty() || UserNameText.getText().isEmpty() ||
                            AgeText.getText().isEmpty() || DOB.getValue().equals(null) || Gender == 'e'
            ) {
                new Alert(Alert.AlertType.ERROR, "Please Check you have filled the required* Fields").showAndWait();
                return;
            }
        } catch ( Exception e ){
            System.err.println(e.getMessage());
        }

        SQLHelper Helper = new SQLHelper();
            if( UserNameText.getText().length() < 4 || UserNameText.getText().length() > 16 ){
                new Alert(Alert.AlertType.ERROR, "Please check that username is atleast 4 charecters long and at most 16 charecters ").showAndWait();
                return;
            }
            if( !Character.isAlphabetic( UserNameText.getText().charAt(0) ) ) {
            new Alert(Alert.AlertType.ERROR, "Please start your Username with a letter").showAndWait();
            return;
            }
            if( Integer.valueOf(AgeText.getText()) < 0 || Integer.valueOf(AgeText.getText()) > 105 )
            if( PasswordText.getText().length() < 4 || PasswordText.getText().length() > 16 ){
                new Alert(Alert.AlertType.ERROR, "Please check that password is atleast 4 charecters long and at most 16 charecters ").showAndWait();
                return;
            }
            if( !PasswordText.getText().equals(ConfirmPassText.getText()) ){
                new Alert(Alert.AlertType.ERROR, "Please check that password is equal to confirmed password!").showAndWait();
                return;
            }
            // This should return true if another user exists, if so prompt the user that name is already in use
        try {
            // open connection to DB
            Helper.Init();
            if( Helper.findByUserName(UserNameText.getText()).next() ) {
                new Alert(Alert.AlertType.ERROR, "Username already exists! Please choose another username").showAndWait();
                Helper.die();
                return;
            }

            // set the USER ID
            int ID = Helper.CreateID();
            // Format the date for SQL (Note: I am sad to say this but this was copied from the internet, I tried using Simple datetimeformatter and datetimeformatter none worked except this, will come back to it later)
            DOB.setConverter(new StringConverter<LocalDate>() {
                String pattern = "yyyy-MM-dd";
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                {
                    DOB.setPromptText(pattern.toLowerCase());
                }

                @Override public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            });
            new Alert(Alert.AlertType.ERROR, "THIS IS THE DATE!! " + DOB.getValue().toString()).showAndWait();

            // Insert the user data into the tables
            Helper.InsertIntoUsers(
                    ID , UserNameText.getText() , FirstNameText.getText() , LastNameText.getText() , PasswordText.getText() , EmailText.getText() ,
                    DOB.getValue().toString(),Integer.valueOf(AgeText.getText()),"0" + TelephoneText.getText(), "0" + AltPhoneText.getText(), AddressText.getText(),
                    BloodTypeBox.getValue().toString(),4,Gender
            );
            Helper.InsertIntoPATIENTS(ID,"NULL","NULL","NULL");
            Main.MainProgram.closeReg();
            Main.MainProgram.showLogin();
            // if something wrong happens show error
        } catch (Exception e ){
            new Alert(Alert.AlertType.ERROR, "DB Error! " + e.getMessage()).showAndWait();
            e.printStackTrace();
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
