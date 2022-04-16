package Database;

import Database.DBConnection;
import model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class CountryQuery {



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

    public static void checkDateConversion(){
        System.out.println("CREATE DATE TEST");
        String sql = "select Create_Date from countries";
        try{
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Timestamp ts = rs.getTimestamp("Create_Date");
                System.out.println("CD: " + ts.toLocalDateTime().toString());
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
