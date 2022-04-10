package Database;

import model.Customer;
import model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class SearchCustomers {


   /* public static ObservableList<Customer> getCustomer() throws SQLException{
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String searchStatement = "SELECT * from customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID INNER JOIN countries AS co ON co.Country_ID=d.COUNTRY_ID;";

        DBSearch.setPreparedStatemnet(DBConnection.getConnection(), searchStatement);
        PreparedStatment preparedStatment = DBSearch.getPreparedStatement();


    } */




}
