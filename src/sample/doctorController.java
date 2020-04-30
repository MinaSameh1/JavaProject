package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class doctorController {

    @FXML
    private TableView<Patient> tablePatientsView;
    @FXML
    private TableView<Vists> tableVisitsView;
    @FXML
    private TextField
            knownDisease,
            prescriptionText,
            questionText,
            notesText;
    @FXML
    private Label complainsLabel;

    public void updatePatient(){
        SQLHelper helper = new SQLHelper();
        try {
            helper.Init();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            helper.die();
        }
    }

    public void showAbout(){
        Main.MainProgram.showAbout();
    }
    public void exitProgram(){
        Main.MainProgram.DIE();
    }
}
