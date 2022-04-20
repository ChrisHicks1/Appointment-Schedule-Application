package Database;

import javafx.scene.control.ComboBox;
import model.Countries;
import model.Customer;
import model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerQuery {


    public static ObservableList<Customer> getCustomer() throws SQLException{
        ObservableList<Customer> customersList = FXCollections.observableArrayList();


        try {

            String sql = "SELECT * FROM customers AS c LEFT JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID LEFT JOIN countries AS co ON co.Country_ID = d.COUNTRY_ID";


            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int Customer_ID = rs.getInt("Customer_ID");
                String Customer_Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal_Code = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                String Country = rs.getString("Country");
                String Division = rs.getString("Division");//.getString("Division");
                int Division_ID = rs.getInt("Division_ID");

                Customer c = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Country, Division, Division_ID);

                customersList.add(c);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return customersList;
    }

    public static void createCustomer(String Customer_Name, String Address, String Postal_Code, String Phone, String Country, String Division){
        try{
            String sqlcc = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pscc = DBConnection.getConnection().prepareStatement(sqlcc);


            pscc.setString(1, Customer_Name);
            pscc.setString(2, Address);
            pscc.setString(3, Postal_Code);
            pscc.setString(4, Phone);
            pscc.setString(5, String.valueOf(Country));
            pscc.setString(6, String.valueOf(Division));

            pscc.execute();

            ResultSet rs = pscc.getResultSet();
            rs.next();
            int Customer_ID = rs.getInt(1);
/*
            String sqlc = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psc = DBConnection.getConnection().prepareStatement(sqlc);


            psc.setInt(1, Customer_ID);
            psc.setString(2, Customer_Name);
            pscc.setString(3, Address);
            pscc.setString(4, Postal_Code);
            pscc.setString(5, Phone);
            pscc.setString(6, String.valueOf(Country));
            pscc.setString(7, String.valueOf(Division));

            psc.execute();

*/
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static int modifyCustomer (int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, Countries Country, Division Division) throws SQLException {

        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Country = ?, Division = ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sql);


        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setString(5, String.valueOf(Country));
        ps.setString(6, String.valueOf(Division));
        ps.setInt(7, Customer_ID);


        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }



    public static int deleteCustomer(int Customer_ID) throws SQLException {
        String sqld = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sqld);
        ps.setInt(1, Customer_ID);
        int results = ps.executeUpdate();
        return results;
    }





    public static void select() throws SQLException {
        String sqls = "SELECT * FROM customers";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sqls);
        ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Customer_ID = rs.getInt("Customer_ID");
                String Customer_Name = rs.getString("Customer_Name");
                System.out.print(Customer_ID + " | ");
                System.out.print(Customer_Name + "\n");
            }
        }


}
