package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TableViewHelper {

    public List<User> getUsersAsList() {
        SQLHelper helper = new SQLHelper();
        try {
            List<User> list = new ArrayList<>();
            helper.Init();
            ResultSet rs = helper.getUsers();
            while (rs.next()) {
                User user = helper.getUserProp(rs);
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR " + e.getMessage());
            return null;
        } finally {
            helper.die();
        }
    }

    public List<User> getUsersAsListByName(String UserName) {
        SQLHelper helper = new SQLHelper();
        try {
            List<User> list = new ArrayList<>();
            helper.Init();
            ResultSet rs = helper.findByUserName(UserName);
            while (rs.next()) {
                User user = helper.getUserProp(rs);
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR " + e.getMessage());
            return null;
        } finally {
            helper.die();
        }
    }

    public List<Worker> getWorkersAsList() {
        SQLHelper helper = new SQLHelper();
        try {
            List<Worker> list = new ArrayList<>();
            helper.Init();
            ResultSet rs = helper.getWorkers();
            while (rs.next()) {
                Worker worker = helper.getWorkerProp(rs);
                list.add(worker);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR " + e.getMessage());
            return null;
        } finally {
            helper.die();
        }
    }

    public List<Worker> getWorkersAsListById(int id) {
        SQLHelper helper = new SQLHelper();
        try {
            List<Worker> list = new ArrayList<>();
            helper.Init();
            ResultSet rs = helper.findWorkerById(id);
            while (rs.next()) {
                Worker user = helper.getWorkerProp(rs);
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR " + e.getMessage());
            return null;
        } finally {
            helper.die();
        }
    }

    public List<Patient> getPatientsAsList() {
        SQLHelper helper = new SQLHelper();
        try {
            List<Patient> list = new ArrayList<>();
            helper.Init();
            ResultSet rs = helper.getPatients();
            while (rs.next()) {
                Patient patient = helper.getPatientProp(rs);
                list.add(patient);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR " + e.getMessage());
            return null;
        } finally {
            helper.die();
        }
    }

    public List<Vists> getVisitsAsList() {
        SQLHelper helper = new SQLHelper();
        try {
            List<Vists> list = new ArrayList<>();
            helper.Init();
            ResultSet rs = helper.getVisits();
            while (rs.next()) {
                Vists vists = helper.getVisitsProp(rs);
                list.add(vists);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR " + e.getMessage());
            return null;
        } finally {
            helper.die();
        }
    }

    // This is hardcoded for now sadly, the problem is with my User class, its not a problem that needs to refactor the code tho....
    private String[] cols = {
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

    // hey atleast this one isn't hardcoded :D
    private String[] UserCols;

    public TableView<User> getUsersTable() {
        TableView<User> tabView = new TableView();
        SQLHelper sqlHelper = new SQLHelper();
        TableViewHelper tableViewHelper = new TableViewHelper();
        ObservableList<User> usersList = FXCollections.observableArrayList(tableViewHelper.getUsersAsList());
        try {
            sqlHelper.Init();
            ResultSet rs = sqlHelper.getUsers();
            UserCols = new String[rs.getMetaData().getColumnCount()];
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                UserCols[i - 1] = rs.getMetaData().getColumnName(i);
            }
            tabView.setItems(usersList);
            tabView.setColumnResizePolicy(
                    TableView.CONSTRAINED_RESIZE_POLICY
            );
            for (int i = 0; i <
                    cols.length; i++) {
                TableColumn<User, Object> col = new TableColumn<>(UserCols[i]);
                col.setCellValueFactory(new PropertyValueFactory<>(cols[i]));
                tabView.getColumns().add(col);
            }
            return tabView;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlHelper.die();
        }
        return null;
    }


}
