package Database;

import Database.DBConnection;
import model.Contacts;
import model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class CountryDB {


    /**Retrieves all Countries from countries table*/
    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries> clist = FXCollections.observableArrayList();

        try{
            String sql = "Select * from countries";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int country_ID = rs.getInt("Country_ID");
                String country_Name = rs.getString("Country");
                Countries C = new Countries(country_ID, country_Name);
                clist.add(C);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clist;
    }


    /**Retrieves Country information from countries table based on Country Name*/
    public static Countries getCountry_ID(String countryName) throws SQLException {
        String sql = "SELECT * FROM countries WHERE Country = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setString(1, countryName);
        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Countries countries = new Countries(
                        rs.getInt("Country_ID"),
                        rs.getString("Country"));
                return countries;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }return null;
    }

}
