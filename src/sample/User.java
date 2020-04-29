package sample;

import javafx.beans.property.*;

import java.time.LocalDate;

// Model Class for our Users
public class User {
    private final IntegerProperty ID;
    private final StringProperty UserName;
    private final StringProperty FirstName;
    private final StringProperty LastName;
    private final StringProperty Password;
     private final StringProperty Email;
    private final ObjectProperty<LocalDate> Dob;
    private final IntegerProperty Age;
    private final StringProperty Telephone;
    private final StringProperty AltTelephone;
    private final StringProperty Address;
    private final StringProperty BloodType;
    private final IntegerProperty UserType;
    private final StringProperty Gender;

    // Constructor
    public User(int id, String userName, String firstName, String lastName, String password, String email,
                LocalDate dob, int age, String telephone, String altTelephone, String address, String bloodType,
                int userType, String gender) {
        ID = new SimpleIntegerProperty(id);
        UserName = new SimpleStringProperty(userName);
        FirstName = new SimpleStringProperty(firstName);
        LastName = new SimpleStringProperty(lastName);
        Password = new SimpleStringProperty(password);
        Email = new SimpleStringProperty(email);
        Dob = new SimpleObjectProperty<LocalDate>(dob);
        Age = new SimpleIntegerProperty(age);
        Telephone = new SimpleStringProperty(telephone);
        AltTelephone = new SimpleStringProperty(altTelephone);
        Address = new SimpleStringProperty(address);
        BloodType = new SimpleStringProperty(bloodType);
        UserType = new SimpleIntegerProperty(userType);
        Gender = new SimpleStringProperty(gender);
    }

    public int getID() {
        return ID.get();
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public String getUserName() {
        return UserName.get();
    }

    public void setUserName(String UserName) {
        this.UserName.set(UserName);
    }

    public StringProperty UserNameProperty() {
        return UserName;
    }

    public String getFirstName() {
        return FirstName.get();
    }

    public void setFirstName(String firstName) {
        this.FirstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return FirstName;
    }

    public String getLastName() {
        return LastName.get();
    }

    public void setLastName(String lastName) {
        this.LastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return LastName;
    }

    public String getAddress() {
        return Address.get();
    }

    public void setAddress(String Address) {
        this.Address.set(Address);
    }

    public StringProperty AddressProperty() {
        return Address;
    }

    public int getAge() {
        return Age.get();
    }

    public void setAge(int Age) {
        this.Age.set(Age);
    }

    public IntegerProperty AgeProperty() {
        return Age;
    }

    public String getPassword() {
        return Password.get();
    }

    public void setPassword(String Password) {
        this.Password.set(Password);
    }

    public StringProperty PasswordProperty() {
        return Password;
    }

    public LocalDate getDob() {
        return Dob.get();
    }

    public void setDob(LocalDate Dob) {
        this.Dob.set(Dob);
    }

    public ObjectProperty<LocalDate> DobProperty() {
        return Dob;
    }

    public String getEmail() {
        return Email.get();
    }

    public void setEmail(String Email) {
        this.Email.set(Email);
    }

    public StringProperty EmailProperty() {
        return Email;
    }
    
    public String getTelephone() {
        return Telephone.get();
    }

    public void setTelephone(String Telephone) {
        this.Telephone.set(Telephone);
    }

    public StringProperty TelephoneProperty() {
        return Telephone;
    }

    public String getAltTelephone() {
        return AltTelephone.get();
    }

    public void setAltTelephone(String AltTelephone) {
        this.AltTelephone.set(AltTelephone);
    }

    public StringProperty AltTelephoneProperty() {
        return AltTelephone;
    }

    public String getBloodType() {
        return BloodType.get();
    }

    public void setBloodType(String BloodType) {
        this.BloodType.set(BloodType);
    }

    public StringProperty BloodTypeProperty() {
        return BloodType;
    }

    public int getUserType() {
        return UserType.get();
    }

    public void setUserType(int UserType) {
        this.UserType.set(UserType);
    }

    public IntegerProperty UserTypeProperty() {
        return UserType;
    }

    public String getGender() {
        return Gender.get();
    }

    public void setGender(String Gender) {
        this.Gender.set(Gender);
    }

    public StringProperty GenderProperty() {
        return Gender;
    }
}
