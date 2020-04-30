package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class adminController implements Initializable {
    @FXML
    private TableView tabView = null;

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
        ObservableList<User> usersList = FXCollections.observableArrayList(tableViewHelper.getUsersAsList());
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

    public void closeAdmin(){
        Main.MainProgram.DIE();
    }

    public void openLogin(){
        Main.MainProgram.startLogin();
        Main.MainProgram.closeAdmin();
    }

    public void resetDatabase(){
        SQLHelper helper = new SQLHelper();
        try {
            helper.Init();
            helper.resetDatabase();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error! " + e.getMessage()).showAndWait();
        }
        new Alert(Alert.AlertType.ERROR, "Success database reset! Please relogin in").showAndWait();
        Main.MainProgram.startLogin();
    }
}
