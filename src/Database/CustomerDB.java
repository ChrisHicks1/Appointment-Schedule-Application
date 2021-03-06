package Database;

import model.Customer;
import model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDB {


    /**Retrieves all Customer information from customers table*/
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
                String Division = rs.getString("Division");
                int Division_ID = rs.getInt("Division_ID");

                Customer c = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Country, Division, Division_ID);

                customersList.add(c);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return customersList;
    }

/**Creates new customer in customers table*/
    public static int createCustomer(String Customer_Name, String Address, String Postal_Code, String Phone, String divisionName) throws SQLException {
        Division division = DivisionDB.getDivision_ID(divisionName);

            String sqlcc = "INSERT INTO customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(NULL, ?, ?, ?, ?, ?)";

            PreparedStatement pscc = DBConnection.getConnection().prepareStatement(sqlcc);


            pscc.setString(1, Customer_Name);
            pscc.setString(2, Address);
            pscc.setString(3, Postal_Code);
            pscc.setString(4, Phone);
            pscc.setInt(5, division.getDivisionId());



            int rowsAffected = pscc.executeUpdate();

            return rowsAffected;

    }


    /**Updates customer in customers table based on Customer_ID*/
    public static int modifyCustomer (int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, String divisionName) throws SQLException {

        Division division = DivisionDB.getDivision_ID(divisionName);

        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sql);


        ps.setString(1, Customer_Name);
        ps.setString(2, Address);
        ps.setString(3, Postal_Code);
        ps.setString(4, Phone);
        ps.setInt(5, division.getDivisionId());
        ps.setInt(6, Customer_ID);


        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }


/**Deletes customer from customers table based on Customer_ID*/
    public static int deleteCustomer(int Customer_ID) throws SQLException {
        String sqld = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.conn.prepareStatement(sqld);
        ps.setInt(1, Customer_ID);
        int results = ps.executeUpdate();
        return results;
    }

}
