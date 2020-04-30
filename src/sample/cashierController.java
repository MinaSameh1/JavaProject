package sample;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class cashierController implements Initializable {
    @FXML
    private ComboBox visitTypeCombo;
    @FXML
    private TextField notesText,
            totalText,
            patientIdText,
            purposeText,
            ExtraText;

    @FXML
    private TableView<Visits> tableVisitsView;
    TableViewHelper tableViewHelper = new TableViewHelper();
    ObservableList<Visits> tableVisitsList = null;

    private String[] VisitsCols;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startVisitsTable();
        visitTypeCombo.getItems().addAll("reveal", "consultation", "Others");
    }

    public void save(){
        SQLHelper helper = new SQLHelper();
        try {
            helper.Init();
            int ID = helper.CreateVisitsID();
            helper.InsertIntoVISTS(
                    ID, Integer.parseInt(patientIdText.getText()), purposeText.getText(), visitTypeCombo.getSelectionModel().getSelectedItem().toString(),
                    ExtraText.getText(), Double.parseDouble(totalText.getText())
            );
            new Alert(Alert.AlertType.INFORMATION, "Visit added successfully!").showAndWait();
            tableVisitsList = FXCollections.observableArrayList(tableViewHelper.getVisitsAsList());
            tableVisitsView.setItems(tableVisitsList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            helper.die();
        }
    }

    public void exitProgram(){
        Main.MainProgram.closeCashier();
    }

    public void logOut(){
        Main.MainProgram.startLogin();
        Main.MainProgram.closeCashier();
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
