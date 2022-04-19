package Database;
import javafx.scene.control.Alert;
import model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class AppointmentQuery {

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> aList = FXCollections.observableArrayList();


        try {

            String sql = "SELECT * FROM appointments AS a LEFT JOIN customers AS c ON a.Customer_ID = c.Customer_ID INNER JOIN users AS u ON u.User_ID = a.User_ID LEFT JOIN contacts AS co ON co.Contact_ID = a.Contact_ID";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return aList;
    }

    public static int createAppointment(String Title, String Description, String Location, String Contact_Name, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID, int User_ID, int Contact_ID) throws SQLException {

        String sqla = "INSERT INTO appointments(Title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement psa = DBConnection.getConnection().prepareStatement(sqla);

        psa.setString(1, Title);
        psa.setString(2, Description);
        psa.setString(3, Location);
        psa.setString(4, Contact_Name);
        psa.setString(5, Type);
        psa.setTimestamp(6, Timestamp.valueOf(Start));
        psa.setTimestamp(7, Timestamp.valueOf(End));
        psa.setInt(8, Customer_ID);
        psa.setInt(9, User_ID);
        psa.setInt(10, Contact_ID);

        int rowsAffected = psa.executeUpdate();

        return rowsAffected;
        //  ResultSet rs = psa.getResultSet();
        //  rs.next();
    }

    public static int deleteAppointment(int Appointment_ID) throws SQLException {
        String sqld = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sqld);
        ps.setInt(1, Appointment_ID);
        int results = ps.executeUpdate();
        return results;
    }

    public static void select() throws SQLException {
        String sqls = "SELECT * FROM appointments AS a INNER JOIN customers AS c ON a.Customer_ID = c.Customer_ID";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sqls);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Customer_Name = rs.getString("Customer_Name");
            System.out.print(Appointment_ID + " | ");
            System.out.print(Customer_Name + "\n");
        }
    }


  /*  public static ObservableList<Appointments> getAssocCustomers(int Customer_ID) throws SQLException{
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String sqlac = "SELECT * FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID NOT WHERE Customer_ID = ?";

        PreparedStatement ps = DBConnection.conn.prepareStatement(sqlac);
        ps.setInt(1, Customer_ID);

        try {
            ps.executeUpdate();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Appointments newAppointment = new Appointments(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        rs.getTimestamp("Start").toLocalDateTime(),
                        rs.getDate("Start").toLocalDate(),
                        rs.getTimestamp("End").toLocalDateTime(),
                        rs.getDate("End").toLocalDate(),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                );
                appointments.add(newAppointment);
            } return appointments;
        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
        }
        }*/



}

