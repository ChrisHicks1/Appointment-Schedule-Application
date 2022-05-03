package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Countries;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDB {


    /**Retrieves all Divisions from first_level_divisions table*/
    public static ObservableList<Division> getAllDivisions() {
        ObservableList<Division> dlist = FXCollections.observableArrayList();
        try {
            String sqld = "SELECT * FROM first_level_divisions AS f INNER JOIN countries AS c on c.Country_ID = f.Country_ID";
            PreparedStatement psd = DBConnection.getConnection().prepareStatement(sqld);

            ResultSet rs = psd.executeQuery();

            while (rs.next()) {
                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int Country_ID = rs.getInt("Country_ID");

                Division d = new Division(Division_ID, Division, Country_ID);
                dlist.add(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dlist;
    }


    /**Returns list of Divisions in a Country from first_level_divisions table based on selected Country_ID*/
    public static ObservableList<Division> getStates(String country) throws SQLException {
        Countries c = CountryDB.getCountry_ID(country);
        ObservableList<Division> slist = FXCollections.observableArrayList();
        String sqls = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";

        PreparedStatement pss = DBConnection.getConnection().prepareStatement(sqls);
        pss.setInt(1, c.getCountryId());

        try {
            ResultSet rs = pss.executeQuery();

            while (rs.next()) {

                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int Country_ID = rs.getInt("Country_ID");

                Division s = new Division(Division_ID, Division, Country_ID);
                slist.add(s);
            }
            return slist;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**Retrieves selected Division information from first_level_divisions table*/
    public static Division getDivision_ID(String divisionName) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setString(1, divisionName);
        try {
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int Country_ID = rs.getInt("Country_ID");
                Division division = new Division(Division_ID, Division, Country_ID);
                return division;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
