package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Division {

    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    public int divisionId;
    public String division;
    public int countryId;

    /**Division Constructor*/
    public Division(int divisionId, String division, int countryId){
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }


    /**Division Getters and Setters*/
    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }


    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

}
