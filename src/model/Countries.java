package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




public class Countries {

    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    private int countryId;
    private String countryName;


    /**Country Constructor*/
    public Countries(int countryId, String countryName){
        this.countryId = countryId;
        this.countryName = countryName;
    }


    /**Country Getters and Setters*/
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
