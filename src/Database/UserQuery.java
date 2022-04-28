package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {

    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> uList = FXCollections.observableArrayList();

        try{
            String sqlu = "SELECT * FROM users";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlu);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int User_ID = rs.getInt("User_ID");
                String User_Name = rs.getString("User_Name");
                String Password = rs.getString("Password");

                Users u = new Users(User_ID, User_Name, Password);
                uList.add(u);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return uList;

    }

    public static boolean checkUserAndPassword(String User_Name, String Password) throws SQLException {

        String sqlus = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlus);

        ps.setString(1, User_Name);
        ps.setString(2, Password);


        try{
        ps.execute();
        ResultSet rs = ps.getResultSet();
               return rs.next();
           } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }




}
