package Database;

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
                String Division = rs.getString("Division");
                String Country = rs.getString("Country");
                int Division_ID = rs.getInt("Division_ID");

                Customer c = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division, Country, Division_ID);

                customersList.add(c);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return customersList;
    }

    public static void createCustomer(){

    }

    public static void modifyCustomer(){

    }

    public static void deleteCustomer(){

    }





}
