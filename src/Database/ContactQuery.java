package Database;


import model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ContactQuery {


    public static ObservableList<Contacts> getAllContacts() {
      /*  ObservableList<Contacts> coList = FXCollections.observableArrayList();

        try {
            String sqlco = "SELECT * FROM contacts AS co INNER JOIN appointments AS a ON a.Appointment_ID = co.Appointment_ID INNER JOIN customers AS c ON c.Customer_ID = co.Customer_ID";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlco);

            ResultSet rs = ps.executeQuery();

            while (rs.next());
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Type = rs.getString("Type");
            LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
            int Customer_ID = rs.getInt("Customer_ID");

            Contacts co = new Contacts(Appointment_ID, Title, Description, Type, Start, End, Customer_ID);
            coList.add(co);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return coList; */
       return null;

    }
}
