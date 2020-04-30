package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class doctorAssistantController implements Initializable {
    @FXML
    private TableView<Patient> tablePatientsView;
    @FXML
    private TableView<Visits> tableVisitsView;

    TableViewHelper tableViewHelper = new TableViewHelper();

    ObservableList<Patient> tablePatientList = null;
    ObservableList<Visits> tableVisitsList = null;

    private String[] patientColsNames = {
            "Id",
            "KnownDiseases",
            "Notes",
            "Prescription",
            "Question",
            "Complains"
    };

    private String[] PatientsCols;
    private String[] VisitsCols;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startPatientsTable();
        startVisitsTable();
    }

    public void showAbout(){
        Main.MainProgram.showAbout();
    }
    public void exitProgram(){
        Main.MainProgram.DIE();
    }

    // TODO: Make this a method in the TableViewHelper class
    public void startPatientsTable(){
        SQLHelper sqlHelper = new SQLHelper();
        tablePatientList = FXCollections.observableArrayList(tableViewHelper.getPatientsAsList());
        try {
            sqlHelper.Init();
            ResultSet rs = sqlHelper.getPatients();
            PatientsCols = new String[rs.getMetaData().getColumnCount()];
            for( int i=1; i <= rs.getMetaData().getColumnCount(); i++ ){
                PatientsCols[i-1] = rs.getMetaData().getColumnName(i);
            }
            tablePatientsView.setItems(tablePatientList);
            tablePatientsView.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY);
            for ( int i = 0; i < PatientsCols.length; i++ ) {
                TableColumn<Patient, Object> col  = new TableColumn<>(PatientsCols[i]);
                col.setCellValueFactory(new PropertyValueFactory<>(patientColsNames[i]));
                tablePatientsView.getColumns().add(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.die();
        }
    }

    public void startVisitsTable(){
        SQLHelper sqlHelper = new SQLHelper();
        tableVisitsList = FXCollections.observableArrayList(tableViewHelper.getVisitsAsList());
        try {
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
