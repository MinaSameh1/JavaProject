package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {
    
    private final IntegerProperty Id;
    private final StringProperty KnownDiseases;
    private final StringProperty Notes;
    private final StringProperty Prescription;
    private final StringProperty Question;
    private final StringProperty Complains;

    // Constructor for patient
    public Patient(int id, String knownDiseases, String prescription, String Complains, String Question, String Notes) {
        this.Id = new SimpleIntegerProperty(id);
        this.KnownDiseases  = new SimpleStringProperty( knownDiseases   );
        this.Prescription   = new SimpleStringProperty( prescription)   ;
        this.Complains      = new SimpleStringProperty( Complains       );
        this.Question       = new SimpleStringProperty( Question        );
        this.Notes          = new SimpleStringProperty( Notes         );
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

    public String getQuestion() {
        return Question.get();
    }

    public StringProperty questionProperty() {
        return Question;
    }

    public void setQuestion(String question) {
        this.Question.set(question);
    }

    public String getComplains() {
        return Complains.get();
    }

    public StringProperty complainsProperty() {
        return Complains;
    }

    public void setComplains(String complains) {
        this.Complains.set(complains);
    }
    public String getNotes() {
        return Notes.get();
    }

    public StringProperty notesProperty() {
        return Notes;
    }

    public void setNotes(String notes) {
        this.Notes.set(notes);
    }

}
