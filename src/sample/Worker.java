package sample;

import javafx.beans.property.*;

public class Worker {
    private final IntegerProperty Id;
    private final DoubleProperty Salary;
    private final StringProperty WorkTime;
    private final StringProperty Notes;

    public Worker(int id, double salary, String workTime, String notes) {
        Id = new SimpleIntegerProperty(id);
        Salary = new SimpleDoubleProperty(salary);
        WorkTime = new SimpleStringProperty(workTime);
        Notes = new SimpleStringProperty(notes);
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
    
    public double getSalary() {
        return Salary.get();
    }

    public void setSalary(int Salary) {
        this.Salary.set(Salary);
    }

    public DoubleProperty SalaryProperty() {
        return Salary;
    }

    public String getWorkTime() {
        return WorkTime.get();
    }

    public void setWorkTime(String WorkTime) {
        this.WorkTime.set(WorkTime);
    }

    public StringProperty WorkTimeProperty() {
        return WorkTime;
    }
    
    public String getNotes() {
        return Notes.get();
    }

    public void setNotes(String Notes) {
        this.Notes.set(Notes);
    }

    public StringProperty NotesProperty() {
        return Notes;
    }
}
