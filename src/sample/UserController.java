package sample;

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

public class UserController implements Initializable {
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
    RadioButton Mradio, Fradio;
    @FXML
    PasswordField PasswordText,
            ConfirmPassText;
    @FXML
    ComboBox BloodTypeBox,userTypeCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add items to the comboBox
        BloodTypeBox.getItems().addAll("A", "B", "AB", "O");
        userTypeCombo.getItems().addAll("admin", "doctor", "doctor assistant", "Cashier", "Patient");
        // force the field to be numeric only
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            // Regex to check if the numbers
            if ( newText.matches("^[0-9]+$") ) {
                return change;
            }
            return null;
        };

        UnaryOperator<TextFormatter.Change> spaceFilter = change -> {
            if ( change.getText().equals(" ") ) {
                change.setText("");
            }
            return change;
        };

        // make telephone,Altphone,Age only numeric
        AgeText.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        TelephoneText.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        AltPhoneText.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));


        // Make password and username no space
        UserNameText.setTextFormatter(new TextFormatter<String>(spaceFilter));
        PasswordText.setTextFormatter(new TextFormatter<String>(spaceFilter));
    }

    public void ResetReg() {
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
        else if ( Fradio.isSelected() )
            Gender = 'F';

        // Make sure all fields are correctly filled
        try {
            // Make sure we don't have any spaces
            UserNameText.setText(UserNameText.getText().replaceAll("\\s+", ""));
            PasswordText.setText(PasswordText.getText().replaceAll("\\s+", ""));
            // check if the required fields are entered
            if (
                    FirstNameText.getText().isEmpty() || LastNameText.getText().isEmpty() || EmailText.getText().isEmpty() ||
                            PasswordText.getText().isEmpty() || ConfirmPassText.getText().isEmpty() || UserNameText.getText().isEmpty() ||
                            AgeText.getText().isEmpty() || DOB.getValue().equals(null) || Gender == 'e'
            ) {
                // if not then stop the registration and tell the user to fill the required fields
                new Alert(Alert.AlertType.ERROR, "Please Check you have filled the required* Fields").showAndWait();
                return;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        SQLHelper Helper = new SQLHelper();
        if ( UserNameText.getText().length() < 4 || UserNameText.getText().length() > 16 ) {
            new Alert(Alert.AlertType.ERROR, "Please check that username is atleast 4 charecters long and at most 16 charecters ").showAndWait();
            return;
        }
        if ( !Character.isAlphabetic(UserNameText.getText().charAt(0)) ) {
            new Alert(Alert.AlertType.ERROR, "Please start your Username with a letter").showAndWait();
            return;
        }
        if ( Integer.valueOf(AgeText.getText()) < 0 || Integer.valueOf(AgeText.getText()) > 105 )
            if ( PasswordText.getText().length() < 4 || PasswordText.getText().length() > 16 ) {
                new Alert(Alert.AlertType.ERROR, "Please check that password is atleast 4 charecters long and at most 16 charecters ").showAndWait();
                return;
            }
        if ( !PasswordText.getText().equals(ConfirmPassText.getText()) ) {
            new Alert(Alert.AlertType.ERROR, "Please check that password is equal to confirmed password!").showAndWait();
            return;
        }
        // This should return true if another user exists, if so prompt the user that name is already in use
        try {
            // open connection to DB
            Helper.Init();
            if ( Helper.findByUserName(UserNameText.getText()).next() ) {
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

                @Override
                public String toString(LocalDate date) {
                    if ( date != null ) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override
                public LocalDate fromString(String string) {
                    if ( string != null && !string.isEmpty() ) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            });

            // Insert the user data into the users and patients
            Helper.InsertIntoUsers(
                    ID, UserNameText.getText(), FirstNameText.getText(), LastNameText.getText(), PasswordText.getText(), EmailText.getText(),
                    DOB.getValue().toString(), Integer.valueOf(AgeText.getText()), "0" + TelephoneText.getText(), "0" + AltPhoneText.getText(), AddressText.getText(),
                    BloodTypeBox.getValue().toString(), userTypeCombo.getSelectionModel().getSelectedIndex(), Gender
            );
            switch (userTypeCombo.getSelectionModel().getSelectedIndex()){
                case 0:
                    Helper.InsertIntoWORKERS(ID, 0, "NULL", "NULL");
                    break;
                case 1:
                    Helper.InsertIntoWORKERS(ID, 0, "NULL", "NULL");
                    break;
                case 2:
                    Helper.InsertIntoWORKERS(ID, 0, "NULL", "NULL");
                    break;
                case 3:
                    Helper.InsertIntoWORKERS(ID, 0, "NULL", "NULL");
                    break;
                case 4:
                    Helper.InsertIntoPATIENTS(ID, "NULL", "NULL", "NULL", "NULL", "NULL");
                    break;
            }

            // SUCCESS TELL THE USER
            new Alert(Alert.AlertType.INFORMATION, "Registration Successful!").showAndWait();

            // Exit addnew
            Main.MainProgram.closeAddNew();
            Main.MainProgram.closeAdmin();
            Main.MainProgram.startAdmin();
        } catch (Exception e) {
            // if something wrong happens show error
            new Alert(Alert.AlertType.ERROR, "Error! " + e.getMessage()).showAndWait();
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

    public void exitReg(){ Main.MainProgram.DIE(); }

    public void openHelp(){
        Main.MainProgram.showHelp("HELPREG");
    }

    public void showAbout(){
        Main.MainProgram.showAbout();
    }


}
