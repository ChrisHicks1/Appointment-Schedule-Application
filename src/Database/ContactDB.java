package Database;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDB {


    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> coList = FXCollections.observableArrayList();

        try {
            String sqlco = "SELECT * FROM contacts";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlco);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int Contact_ID = rs.getInt("Contact_ID");
                String Contact_Name = rs.getString("Contact_Name");
                String Email = rs.getString("Email");


                Contacts co = new Contacts(Contact_ID, Contact_Name, Email);
                coList.add(co);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coList;
    }
    public static Contacts getContact_ID(String contactName) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);


        ps.setString(1, contactName);
        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Contacts contacts = new Contacts(
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name"),
                        rs.getString("Email"));
                return contacts;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return null;
    }
}
