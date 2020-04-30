package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<User> tableUsersView = null;
    @FXML
    private TableView<Worker> tableWorkersView = null;
    @FXML
    private TableView<Patient> tablePatientsView = null;
    @FXML
    private TableView<Vists> tableVisitsView = null;

    TableViewHelper tableViewHelper = new TableViewHelper();

    ObservableList<User> tableUserList = null;
    ObservableList<Worker> tableWorkerList = null;
    ObservableList<Patient> tablePatientList = null;
    ObservableList<Vists> tableVisitsList = null;


    // This is hardcoded for now sadly, the problem is with my User class, its not a problem that needs to refactor the code tho....
    private String[] userColsNames = {
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

    private String[] workerColsNames = {
            "Id",
            "Salary",
            "WorkTime",
            "Notes"
    };

    private String[] patientColsNames = {
            "Id",
            "KnownDiseases",
            "Notes",
            "Prescription",
            "Question",
            "Complains"
    };

    // hey atleast These Ones aren't hardcoded :D
    private String[] UserCols;
    private String[] WorkerCols;
    private String[] PatientsCols;
    private String[] VisitsCols;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startTableUsers();
        startTableWorkers();
        startPatientsTable();
        startVisitsTable();
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
            tableUserList.removeAll(tableUserList);
            tableUserList = FXCollections.observableArrayList(tableViewHelper.getUsersAsListByName(result.get()));
            tableUsersView.setItems(tableUserList);

        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNew(){
        Main.MainProgram.startAddNew();
    }

    public void deleteRow(){
        User user = tableUsersView.getSelectionModel().getSelectedItem();
        SQLHelper sqlHelper = new SQLHelper();
        try {
            sqlHelper.Init();
            sqlHelper.delFromPatients(user.getId());
            sqlHelper.delFromUsers(user.getId());
            new Alert(Alert.AlertType.INFORMATION, "Success deleted user").showAndWait();
            tableUserList = FXCollections.observableArrayList(tableViewHelper.getUsersAsList());
            tableUsersView.setItems(tableUserList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.die();
        }
    }

    public void reloadUsersTable(){
        tableUserList = FXCollections.observableArrayList(tableViewHelper.getUsersAsList());
        tableUsersView.setItems(tableUserList);
    }

    public void reloadWorkersTable(){
        tableWorkerList = FXCollections.observableArrayList(tableViewHelper.getWorkersAsList());
        tableWorkersView.setItems(tableWorkerList);
    }


    public void reloadPatientsTable(){
        tablePatientList = FXCollections.observableArrayList(tableViewHelper.getPatientsAsList());
        tablePatientsView.setItems(tablePatientList);
    }


    public void reloadVisitsTable(){
        tableVisitsList = FXCollections.observableArrayList(tableViewHelper.getVisitsAsList());
        tableVisitsView.setItems(tableVisitsList);
    }

    public void startTableUsers(){
        SQLHelper sqlHelper = new SQLHelper();
        tableUserList = FXCollections.observableArrayList(tableViewHelper.getUsersAsList());
        try {
            sqlHelper.Init();
            ResultSet rs = sqlHelper.getUsers();
            UserCols = new String[rs.getMetaData().getColumnCount()];
            for( int i=1; i <= rs.getMetaData().getColumnCount(); i++ ){
                UserCols[i-1] = rs.getMetaData().getColumnName(i);
            }
            tableUsersView.setItems(tableUserList);
            tableUsersView.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY);
            for (int i = 0; i <
                    userColsNames.length; i++) {
                TableColumn<User, Object> col  = new TableColumn<>(UserCols[i]);
                col.setCellValueFactory(new PropertyValueFactory<>(userColsNames[i]));
                tableUsersView.getColumns().add(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.die();
        }
    }

    public void startTableWorkers(){
        SQLHelper sqlHelper = new SQLHelper();
        tableWorkerList = FXCollections.observableArrayList(tableViewHelper.getWorkersAsList());
        try {
            sqlHelper.Init();
            ResultSet rs = sqlHelper.getWorkers();
            WorkerCols = new String[rs.getMetaData().getColumnCount()];
            for( int i=1; i <= rs.getMetaData().getColumnCount(); i++ ){
                WorkerCols[i-1] = rs.getMetaData().getColumnName(i);
            }
            tableWorkersView.setItems(tableWorkerList);
            tableWorkersView.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY);
            for ( int i = 0; i < WorkerCols.length; i++ ) {
                TableColumn<Worker, Object> col  = new TableColumn<>(WorkerCols[i]);
                col.setCellValueFactory(new PropertyValueFactory<>(workerColsNames[i]));
                tableWorkersView.getColumns().add(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.die();
        }
    }

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
                TableColumn<Vists, Object> col  = new TableColumn<>(VisitsCols[i]);
                col.setCellValueFactory(new PropertyValueFactory<>(VisitsCols[i]));
                tableVisitsView.getColumns().add(col);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.die();
        }
    }

    public void setTableUsers(){
        tableUsersView.setVisible(true);
        tableWorkersView.setVisible(false);
        tablePatientsView.setVisible(false);
        tableVisitsView.setVisible(false);
        reloadUsersTable();
    }

    public void setTableWorkers(){
        tableUsersView.setVisible(false);
        tableWorkersView.setVisible(true);
        tablePatientsView.setVisible(false);
        tableVisitsView.setVisible(false);
        reloadWorkersTable();
    }

    public void setTablePatients(){
        tableUsersView.setVisible(false);
        tableWorkersView.setVisible(false);
        tablePatientsView.setVisible(true);
        tableVisitsView.setVisible(false);
        reloadPatientsTable();
    }

    public void setTableVisits(){
        tableUsersView.setVisible(false);
        tableWorkersView.setVisible(false);
        tablePatientsView.setVisible(false);
        tableVisitsView.setVisible(true);
        reloadVisitsTable();
    }

    public void reloadTables(){
        reloadUsersTable();
        reloadWorkersTable();
        reloadPatientsTable();
        reloadVisitsTable();
    }

}
