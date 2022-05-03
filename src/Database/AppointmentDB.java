package Database;
import model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class AppointmentDB {



    /**Retrieves Appointment information from appointments table*/
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



    /**Creates new Appointment in appointments table*/
    public static int createAppointment(String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID, int User_ID, String contactName) throws SQLException {

        Contacts contacts = ContactDB.getContact_ID(contactName);

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




    /**Updates Appointment information in appointments table based on Appointment_ID*/
    public static int modifyAppointment(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID, int User_ID, String contactName) throws SQLException{
        Contacts contacts = ContactDB.getContact_ID(contactName);

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



    /**Deletes Appointment in appointment table based on Appointment_ID*/
    public static int deleteAppointment(int Appointment_ID) throws SQLException {
        String sqld = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sqld);
        ps.setInt(1, Appointment_ID);
        int results = ps.executeUpdate();
        return results;
    }




    /**Retrieves Appointment information in appointments table based on Start Date Month which is found using today's date and today's date plus 30 days*/
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


    /**Retrieves Appointment information in appointments table based on Start Date Week which is found using today's date and today's date plus 7 days*/
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




    /**Retrieves list of appointments based on a selected customer, used to confirm a customer has no appointments before deleting customer*/
    public static ObservableList<Appointments>getAssocCustomers(int Customer_ID) throws SQLException{
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String sqlac = "SELECT * FROM appointments AS a INNER JOIN contacts AS co ON a.Contact_ID = co.Contact_ID WHERE Customer_ID = ?";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlac);
        ps.setInt(1, Customer_ID);


            ps.execute();
            ResultSet rs = ps.getResultSet();
             try{
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
                int User_ID = rs.getInt("User_ID");
                int Contact_ID = rs.getInt("Contact_ID");
                Appointments aList = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, startDate, End, endDate, Customer_ID, User_ID, Contact_ID);
                appointments.add(aList);
            } return appointments;
        }catch (SQLException ex){
            ex.printStackTrace();
        }return null;
        }


    /**Retrieves list of appointments based on a selected Contact, used for populating Contact Schedule table view based on Contact_Name*/
    public static ObservableList<Appointments>getAssocContacts(String Contact_Name) throws SQLException{
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String sqlac = "SELECT * FROM appointments AS a LEFT JOIN contacts AS co ON co.Contact_ID = a.Contact_ID WHERE Contact_Name = ?";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlac);
        ps.setString(1, Contact_Name);


        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        try{

            while (rs.next()) {
                int Appointment_ID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
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
            } return appointments;
        }catch (SQLException ex){
            ex.printStackTrace();
        }return null;
    }


    /**Retrieves list of appointments based on a selected User, used for populating User Schedule table view based on User_ID*/
    public static ObservableList<Appointments>getAssocUsers(Integer User_ID) throws SQLException{
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        String sqlac = "SELECT * FROM appointments AS a LEFT JOIN contacts AS co ON co.Contact_ID = a.Contact_ID WHERE User_ID = ?";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlac);
        ps.setString(1, String.valueOf(User_ID));


        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        try{

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
                int Contact_ID = rs.getInt("Contact_ID");
                Appointments aList = new Appointments(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, startDate, End, endDate, Customer_ID, User_ID, Contact_ID);
                appointments.add(aList);
            } return appointments;
        }catch (SQLException ex){
            ex.printStackTrace();
        }return null;
    }


}





