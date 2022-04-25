package Database;
import model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

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
                LocalDate startDate = rs.getDate("Start").toLocalDate();
                LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
                LocalDate endDate = rs.getDate("End").toLocalDate();
                int Customer_ID = rs.getInt("Customer_ID");
                int User_ID = rs.getInt("User_ID");
                int Contact_ID = rs.getInt("Contact_ID");
                Appointments a = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, startDate, End, endDate, Customer_ID, User_ID, Contact_ID);
                aList.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return aList;
    }




    public static int createAppointment(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID, int User_ID, String contactName) throws SQLException {

        Contacts contacts = ContactQuery.getContact_ID(contactName);

        String sqla = "INSERT INTO appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement psa = DBConnection.getConnection().prepareStatement(sqla);

        psa.setString(1, Title);
        psa.setString(2, Description);
        psa.setString(3, Location);
        psa.setString(4, Type);
        psa.setTimestamp(5, Timestamp.valueOf(Start));
        psa.setTimestamp(6, Timestamp.valueOf(End));
        psa.setInt(7, Customer_ID);
        psa.setInt(8, User_ID);
        psa.setInt(9, contacts.getContact_ID());


        int rowsAffected = psa.executeUpdate();

        return rowsAffected;
    }





    public static int modifyAppointment(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID, int User_ID, String contactName) throws SQLException{
        Contacts contacts = ContactQuery.getContact_ID(contactName);

        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?,  Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

        PreparedStatement ps = DBConnection.conn.prepareStatement(sql);

        ps.setString(1, Title);
        ps.setString(2, Description);
        ps.setString(3, Location);
        ps.setString(4, Type);
        ps.setTimestamp(5, Timestamp.valueOf(Start));
        ps.setTimestamp(6, Timestamp.valueOf(End));
        ps.setInt(7, Customer_ID);
        ps.setInt(8, User_ID);
        ps.setInt(9, contacts.getContact_ID());
        ps.setInt(10, Appointment_ID);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }




    public static int deleteAppointment(int Appointment_ID) throws SQLException {
        String sqld = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sqld);
        ps.setInt(1, Appointment_ID);
        int results = ps.executeUpdate();
        return results;
    }




    public static ObservableList<Appointments> getMonth() throws SQLException{
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime Month = today.plusDays(30);

        String sql = "SELECT * FROM appointments AS a INNER JOIN contacts AS co ON a.Contact_ID = co.Contact_ID WHERE Start > ? AND Start < ?";

        PreparedStatement ps = DBConnection.conn.prepareStatement(sql);

        ps.setDate(1, java.sql.Date.valueOf(today.toLocalDate()));
        ps.setDate(2, java.sql.Date.valueOf(Month.toLocalDate()));

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Contact_Name = rs.getString("Contact_Name");
            String Type = rs.getString("Type");
            LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDate startDate = rs.getDate("Start").toLocalDate();
            LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
            LocalDate endDate = rs.getDate("End").toLocalDate();
            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");
            int Contact_ID = rs.getInt("Contact_ID");
            Appointments aList = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, startDate, End, endDate, Customer_ID, User_ID, Contact_ID);
            appointments.add(aList);
        }
        return appointments;
}

    public static ObservableList<Appointments> getWeek() throws SQLException{
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime Week = today.plusDays(7);

        String sql = "SELECT * FROM appointments AS a INNER JOIN contacts AS co ON a.Contact_ID = co.Contact_ID WHERE Start > ? AND Start < ?";

        PreparedStatement ps = DBConnection.conn.prepareStatement(sql);

        ps.setDate(1, java.sql.Date.valueOf(today.toLocalDate()));
        ps.setDate(2, java.sql.Date.valueOf(Week.toLocalDate()));

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Contact_Name = rs.getString("Contact_Name");
            String Type = rs.getString("Type");
            LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDate startDate = rs.getDate("Start").toLocalDate();
            LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
            LocalDate endDate = rs.getDate("End").toLocalDate();
            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");
            int Contact_ID = rs.getInt("Contact_ID");
            Appointments aList = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, startDate, End, endDate, Customer_ID, User_ID, Contact_ID);
            appointments.add(aList);
        }
        return appointments;
    }


    public static ObservableList<Appointments> getMinutes() throws SQLException{
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime Minutes = today.plusMinutes(15);

        String sql = "SELECT * FROM appointments AS a INNER JOIN contacts AS co ON a.Contact_ID = co.Contact_ID WHERE Start > ? AND Start < ?";

        PreparedStatement ps = DBConnection.conn.prepareStatement(sql);

        ps.setTime(1, java.sql.Time.valueOf(today.toLocalTime()));
        ps.setTime(2, java.sql.Time.valueOf(Minutes.toLocalTime()));

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Contact_Name = rs.getString("Contact_Name");
            String Type = rs.getString("Type");
            LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDate startDate = rs.getDate("Start").toLocalDate();
            LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
            LocalDate endDate = rs.getDate("End").toLocalDate();
            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");
            int Contact_ID = rs.getInt("Contact_ID");
            Appointments aList = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, startDate, End, endDate, Customer_ID, User_ID, Contact_ID);
            appointments.add(aList);
        }
        return appointments;
    }




//associated customers
//appointments within 15mins
//appointments not in 15mins
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

    public static ObservableList<Appointments>getAssocCustomers(int Customer_ID) throws SQLException{
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String sqlac = "SELECT * FROM appointments WHERE Customer_ID = ?";

        PreparedStatement ps = DBConnection.conn.prepareStatement(sqlac);
        ps.setInt(1, Customer_ID);


            ps.execute();
            ResultSet rs = ps.getResultSet();
             try{
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
        }return null;
        }
        }





