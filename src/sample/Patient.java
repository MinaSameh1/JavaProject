package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {
    
    private final IntegerProperty Id;
    private final StringProperty KnownDiseases;
    private final StringProperty Prescription;

    public Patient(int id, String knownDiseases, String prescription) {
        Id = new SimpleIntegerProperty(id);
        KnownDiseases = new SimpleStringProperty(knownDiseases);
        Prescription = new SimpleStringProperty(prescription);
    }

    public String getKnownDiseases() {
        return KnownDiseases.get();
    }

    public void setKnownDiseases(String KnownDiseases) {
        this.KnownDiseases.set(KnownDiseases);
    }

    public StringProperty KnownDiseasesProperty() {
        return KnownDiseases;
    }

    public String getPrescription() {
        return Prescription.get();
    }

    public void setPrescription(String Prescription) {
        this.Prescription.set(Prescription);
    }

    public StringProperty PrescriptionProperty() {
        return Prescription;
    }
    public int getId() {
        return Id.get();
    }

    public void setId(int Id) {
        this.Id.set(Id);
    }

    public IntegerProperty IdProperty() {
        return Id;
    }
}
