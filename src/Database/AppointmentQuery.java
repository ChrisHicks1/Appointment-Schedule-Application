package Database;
import model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;


public class AppointmentQuery {

    public static ObservableList<Appointments> getAllAppointments(){
        ObservableList<Appointments> aList = FXCollections.observableArrayList();


        try{

            String sql = "SELECT * FROM appointments AS a LEFT JOIN customers AS c ON a.Customer_ID = c.Customer_ID INNER JOIN users AS u ON u.User_ID = a.User_ID LEFT JOIN contacts AS co ON co.Contact_ID = a.Contact_ID";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Contact_Name = rs.getString("Contact_Name");
                String Type = rs.getString("Type");
                LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");
                int Contact_ID = rs.getInt("Contact_ID");
                Appointments a = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID, Contact_ID);
                aList.add(a);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return aList;
    }

    public static int createAppointment(int Appointment_ID, String Title, String Description, String Location, String Type, Timestamp Start, Timestamp End, int Customer_ID, int User_ID, int Contact_ID) {
        try {
            String sqla = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement psa = DBConnection.getConnection().prepareStatement(sqla);

            psa.setInt(1, Appointment_ID);
            psa.setString(2, Title);
            psa.setString(3, Description);
            psa.setString(4, Location);
            psa.setString(5, Type);
            psa.setTimestamp(6, Timestamp.valueOf(String.valueOf(Start)));
            psa.setTimestamp(7, Timestamp.valueOf(String.valueOf(End)));
            psa.setInt(8, Customer_ID);
            psa.setInt(9, User_ID);
            psa.setInt(10, Contact_ID);

            int rowsAffected = psa.executeUpdate();

            return rowsAffected;
            //  ResultSet rs = psa.getResultSet();
            //  rs.next();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }return 0;
    }


}