package sample;

import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

// ADD VISIT TIME
public class Visits {

    private final IntegerProperty VISITID;
    private final IntegerProperty PatientID;
    private final StringProperty EXTRA;
    private final StringProperty PURPOSE ;
    private final StringProperty VISITTYPE;


    private final ObjectProperty<LocalDate> VISIT_TIME;
    private final DoubleProperty COST;

    public Visits(int VISITID , int PatientID, String PURPOSE, String VISITTYPE, Date VISIT_TIME, String EXTRA, double COST) {
        this.VISITID = new SimpleIntegerProperty(VISITID );
        this.PatientID = new SimpleIntegerProperty(PatientID);
        this.PURPOSE  = new SimpleStringProperty(PURPOSE);
        this.EXTRA = new SimpleStringProperty(EXTRA);
        this.VISITTYPE = new SimpleStringProperty(VISITTYPE);
        this.VISIT_TIME = new SimpleObjectProperty<LocalDate>(VISIT_TIME.toLocalDate());
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

    public LocalDate getVISIT_TIME() {
        return VISIT_TIME.get();
    }

    public ObjectProperty<LocalDate> VISIT_TIMEProperty() {
        return VISIT_TIME;
    }

    public void setVISIT_TIME(LocalDate VISIT_TIME) { this.VISIT_TIME.set(VISIT_TIME); }

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