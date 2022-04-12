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
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try {


            String sql = "SELECT * from customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID INNER JOIN countries AS co ON co.Country_ID=d.COUNTRY_ID;";

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

                Customer c = new Customer(rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Division"),
                        rs.getString("Country"),
                        rs.getInt("Division_ID"));

                customers.add(c);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return customers;
    }

    public static void createCustomer(){

    }

    public static void modifyCustomer(){

    }

    public static void deleteCustomer(){

    }


        /*
        String searchStatement = "SELECT * from customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID INNER JOIN countries AS co ON co.Country_ID=d.COUNTRY_ID;";


        DBQuery.setPreparedStatement(DBConnection.getConnection(), searchStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        try{
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while(rs.next()){

                Customer newCustomer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"),
                        rs.getString("Postal_Code"),
                        rs.getString("Phone"),
                        rs.getString("Division"),
                        rs.getString("Country"),
                        rs.getInt("Division_ID")
                );
                customers.add(newCustomer);
            }
            return customers;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

*/


}
