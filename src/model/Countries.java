package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




public class Countries {

    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    private int countryId;
    private String countryName;

    public Countries(int countryId, String countryName){
        this.countryId = countryId;
        this.countryName = countryName;
    }


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



    public static ObservableList<Countries> getAllCountries() {
        return allCountries;
    }


    @Override
    public String toString(){
        return (Integer.toString(countryId) + " " + countryName);
    }
}
