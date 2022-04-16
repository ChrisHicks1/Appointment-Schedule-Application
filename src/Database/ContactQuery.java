package Database;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactQuery {


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
}
