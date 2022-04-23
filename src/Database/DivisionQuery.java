package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Contacts;
import model.Countries;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionQuery {




    public static ObservableList<Division> getAllDivisions(){
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
        }catch (SQLException ex){
            ex.printStackTrace();
        } return dlist;
    }

    public static ObservableList<Division> getStates(){
        ObservableList<Division> slist = FXCollections.observableArrayList();

        try{
            String sqls = "SELECT Division FROM first_level_divisions WHERE Country_ID = 1";
            PreparedStatement pss = DBConnection.getConnection().prepareStatement(sqls);
            ResultSet rs = pss.executeQuery();

            while(rs.next()){
                int Division_ID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int Country_ID = rs.getInt("Country_ID");

                Division s = new Division(Division_ID, Division, Country_ID);
                slist.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }return slist;
    }

    public static Division getDivision_ID( String divisionName) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division_Name = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);



        ps.setString(1, divisionName);
        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Division division = new Division(
                        rs.getInt("Division_ID"),
                        rs.getString("Division_Name"),
                        rs.getInt("Country_ID"));
                return division;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return null;
    }

    public static Countries getCountry_ID( String countryName) throws SQLException {
        String sql = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);



        ps.setString(1, countryName);
        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Countries countries = new Countries(
                        rs.getInt("Country_ID"),
                        rs.getString("Country_Name"));
                return countries;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return null;
    }

}
