package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminController implements Initializable {
    @FXML
    private TableView<User> tabView = null;

    ObservableList<User> usersList = null;

    // This is hardcoded for now sadly, the problem is with my User class, its not a problem that needs to refactor the code tho....
    private String[] cols = {
            "Id",
            "UserName",
            "FirstName",
            "LastName",
            "Password",
            "Email",
            "Dob",
            "Age",
            "Telephone",
            "AltTelephone",
            "Address",
            "BloodType",
            "UserType",
            "Gender"
    };

    // hey atleast this one isn't hardcoded :D
    private  String[] cols_name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SQLHelper sqlHelper = new SQLHelper();
        TableViewHelper tableViewHelper = new TableViewHelper();
        usersList = FXCollections.observableArrayList(tableViewHelper.getUsersAsList());
        try {
            sqlHelper.Init();
            ResultSet rs = sqlHelper.getUsers();
            cols_name = new String[rs.getMetaData().getColumnCount()];
            for( int i=1; i <= rs.getMetaData().getColumnCount(); i++ ){
                cols_name[i-1] = rs.getMetaData().getColumnName(i);
            }
            tabView.setItems(usersList);
            tabView.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY);
            for (int i = 0; i <
                    cols.length; i++) {
                TableColumn<User, Object> col  = new TableColumn<>(cols_name[i]);
                col.setCellValueFactory(new PropertyValueFactory<>(cols[i]));
                tabView.getColumns().add(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
            }
    }

    public void showAbout(){
        Main.MainProgram.showAbout();
    }

    public void showUserTypes(){
        Main.MainProgram.showHelp("USERTYPES");
    }
    public void closeAdmin(){
        Main.MainProgram.DIE();
    }

    public void openLogin(){
        Main.MainProgram.startLogin();
        Main.MainProgram.closeAdmin();
    }

    public void resetDatabase(){
        Optional<ButtonType> Confirm = new Alert(Alert.AlertType.CONFIRMATION, "This will delete ALL DATA INCLUDING PATIENTS AND VISTS! ARE YOU SURE YOU WANT TO DO THIS?").showAndWait();
        ButtonType conf = Confirm.get();
        if( conf == ButtonType.OK )
        {
            SQLHelper helper = new SQLHelper();
            try {
                helper.Init();
                helper.resetDatabase();

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error! " + e.getMessage()).showAndWait();
            }
            new Alert(Alert.AlertType.INFORMATION, "Success database reset! Please relogin in").showAndWait();
            Main.MainProgram.startLogin();
            Main.MainProgram.closeAdmin();
        } else
            return;
    }

    public void searchTable(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("search");
        dialog.setHeaderText("The name you want to be found:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();
        try{
        TableViewHelper tableViewHelper = new TableViewHelper();
        result.ifPresent( search -> {
            System.out.println( result.get() );
            usersList.removeAll( usersList );
            usersList = FXCollections.observableArrayList(tableViewHelper.getUsersAsListByName(result.get()));
            tabView.setItems(usersList);

        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNew(){

    }

    public void deleteRow(){
        User user = tabView.getSelectionModel().getSelectedItem();
        SQLHelper sqlHelper = new SQLHelper();
        TableViewHelper tableViewHelper = new TableViewHelper();
        try {
            sqlHelper.Init();
            sqlHelper.delFromPatients(user.getId());
            sqlHelper.delFromUsers(user.getId());
            new Alert(Alert.AlertType.INFORMATION, "Success deleted user").showAndWait();
            usersList = FXCollections.observableArrayList(tableViewHelper.getUsersAsList());
            tabView.setItems(usersList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.die();
        }

    }

}
