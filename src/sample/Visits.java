package sample;

import javafx.beans.property.*;

import java.time.LocalDate;

// ADD VISIT TIME
public class Visits {

    private final IntegerProperty VISITID;
    private final IntegerProperty PatientID;
    private final StringProperty EXTRA;
    private final StringProperty PURPOSE ;
    private final StringProperty VISITTYPE;


    private final ObjectProperty<LocalDate> VISITTIME;
    private final DoubleProperty COST;

    public Visits(int VISITID , int PatientID, String PURPOSE, String VISITTYPE, LocalDate VISITTIME, String EXTRA, double COST) {
        this.VISITID = new SimpleIntegerProperty(VISITID );
        this.PatientID = new SimpleIntegerProperty(PatientID);
        this.PURPOSE  = new SimpleStringProperty(PURPOSE);
        this.EXTRA = new SimpleStringProperty(EXTRA);
        this.VISITTYPE = new SimpleStringProperty(VISITTYPE);
        this.VISITTIME = new SimpleObjectProperty<LocalDate>(VISITTIME);
        this.COST = new SimpleDoubleProperty(COST);
    }

    public String getPURPOSE() {
        return PURPOSE.get();
    }

    public void setPURPOSE(String PURPOSE) {
        this.PURPOSE.set(PURPOSE);
    }

    public StringProperty PURPOSEProperty() {
        return PURPOSE;
    }

    public String getVISITTYPE() {
        return VISITTYPE.get();
    }

    public void setVISITTYPE(String VISITTYPE) {
        this.VISITTYPE.set(VISITTYPE);
    }

    public StringProperty VISITTYPEroperty() {
        return VISITTYPE;
    }

    public String getEXTRA() {
        return EXTRA.get();
    }

    public void setEXTRA(String EXTRA) {
        this.EXTRA.set(EXTRA);
    }

    public StringProperty EXTRAProperty() {
        return EXTRA;
    }

    public int getVISITID() {
        return VISITID.get();
    }

    public void setVISITID(int VISITID) {
        this.VISITID.set(VISITID);
    }

    public IntegerProperty VISITIDProperty() {
        return VISITID;
    }

    public int getPatientID() {
        return PatientID.get();
    }

    public void setPatientID(int PatientID) {
        this.PatientID.set(PatientID);
    }

    public IntegerProperty PatientIDProperty() {
        return PatientID;
    }

    public IntegerProperty patientIDProperty() {
        return PatientID;
    }

    public StringProperty VISITTYPEProperty() {
        return VISITTYPE;
    }

    public LocalDate getVISITTIME() {
        return VISITTIME.get();
    }

    public ObjectProperty<LocalDate> VISITTIMEProperty() {
        return VISITTIME;
    }

    public void setVISITTIME(LocalDate VISITTIME) { this.VISITTIME.set(VISITTIME); }

    public void setCOST(double COST) { this.COST.set(COST); }

    public double getCOST() {
        return COST.get();
    }

    public void setCOST(int COST) {
        this.COST.set(COST);
    }

    public DoubleProperty COSTProperty() {
        return COST;
    }
}