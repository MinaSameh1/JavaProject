package sample;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TableViewHelper {

    public List<User> getUsersAsList(){
        SQLHelper helper = new SQLHelper();
        try {
            List<User> list = new ArrayList<>();
            helper.Init();
            ResultSet rs = helper.getUsers();
            while( rs.next() ){
                User user = helper.getUserProp(rs);
                list.add(user);
            }
            return list;
        } catch ( Exception e ){
            e.printStackTrace();
            System.err.println("ERROR " + e.getMessage() );
            return null;
        }finally {
            helper.die();
        }
    }

    public List<User> getUsersAsListByName(String name){
        SQLHelper helper = new SQLHelper();
        try {
            List<User> list = new ArrayList<>();
            helper.Init();
            ResultSet rs = helper.findByUserName(name);
            while( rs.next() ){
                User user = helper.getUserProp(rs);
                list.add(user);
            }
            return list;
        } catch ( Exception e ){
            e.printStackTrace();
            System.err.println("ERROR " + e.getMessage() );
            return null;
        }finally {
            helper.die();
        }
    }
}
