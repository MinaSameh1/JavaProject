package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;


public class tableViewFromInternet {
    private ObservableList<ObservableList> data;

    public TableView getUsersTable() {
        TableView tab = null;
        try {
            data = FXCollections.observableArrayList();
                //SQL FOR SELECTING ALL OF CUSTOMER
                SQLHelper helper = new SQLHelper();
                //ResultSet
                helper.Init();
                ResultSet rs = helper.getUsers();

                /**********************************
                 * TABLE COLUMN ADDED DYNAMICALLY *
                 **********************************/
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().getChildren().get(j).toString());
                        }
                    });
                    tab.getColumns().addAll(col);
                    System.out.println("Column ["+i+"] ");
                }
                    /********************************ad
                     * Data added to ObservableList *
                     ********************************/
                    while(rs.next()){
                        //Iterate Row
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                            //Iterate Column
                            row.add(rs.getString(i));
                        }
                        System.out.println("Row [1] added "+row );
                        data.add(row);
                    }
                //FINALLY ADDED TO TableView
                tab.setItems(data);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
        }
        return tab;
    }
}
