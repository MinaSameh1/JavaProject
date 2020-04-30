package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class patientController implements Initializable {

    @FXML
    private Label IdLabel,
            UserNameLabel,
            FirstNameLabel,
            LastNameLabel,
            EmailLabel,
            dobLabel,
            ageLabel,
            telephoneLabel,
            AltPhoneLabel,
            AddressLabel,
            bloodLabel,
            UTLabel,
            GenderLabel
    ;
    @FXML
    private TableView<Visits> tableVisitsView;
    ObservableList<Visits> tableVisitsList = null;

    private String[] VisitsCols;

    public void exitProgram(){
        Main.MainProgram.DIE();
    }

    public void logOut(){
        Main.MainProgram.startLogin();
        Main.MainProgram.closePatient();
    }

    public void showAbout(){
        Main.MainProgram.showAbout();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SQLHelper helper = new SQLHelper();
        try {
            helper.Init();
            User user = helper.getUserPropById(Main.MainProgram.UserID);
            IdLabel.setText(String.valueOf(user.getId()));
            UserNameLabel.setText(user.getUserName());
            FirstNameLabel.setText(user.getFirstName());
            LastNameLabel.setText(user.getLastName());
            EmailLabel.setText(user.getEmail());
            dobLabel.setText(user.getDob().toString());
            ageLabel.setText(String.valueOf(user.getAge()));
            telephoneLabel.setText(user.getTelephone());
            AltPhoneLabel.setText(user.getAltTelephone());
            AddressLabel.setText(user.getAddress());
            bloodLabel.setText(user.getBloodType());
            UTLabel.setText("patient");
            GenderLabel.setText(user.getGender());
        } catch (Exception e) {
            e.printStackTrace();
        }
        startVisitsTable();
    }

    public void startVisitsTable(){
        TableViewHelper tableViewHelper = new TableViewHelper();
        SQLHelper sqlHelper = new SQLHelper();
        try {
            tableVisitsList = FXCollections.observableArrayList(tableViewHelper.getVisitsAsListByPatientId(Main.MainProgram.UserID));
            sqlHelper.Init();
            ResultSet rs = sqlHelper.getVisits();
            VisitsCols = new String[rs.getMetaData().getColumnCount()];
            for( int i=1; i <= rs.getMetaData().getColumnCount(); i++ ){
                VisitsCols[i-1] = rs.getMetaData().getColumnName(i);
            }
            tableVisitsView.setItems(tableVisitsList);
            tableVisitsView.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY);
            for ( int i = 0; i < VisitsCols.length; i++ ) {
                TableColumn<Visits, Object> col  = new TableColumn<>(VisitsCols[i]);
                col.setCellValueFactory(new PropertyValueFactory<>(VisitsCols[i]));
                tableVisitsView.getColumns().add(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.die();
        }
    }
}
