package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private int postal;
    private String phone;
    private String country;
    private String division;
    private int divisionId;

    public Customer(int customerId, String customerName, String address, int postal, String phone, String country, String division, int divisionId){
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
        this.country = country;
        this.division = division;
        this.divisionId = divisionId;
    }


    public int getCustomerId(){return customerId;}

    public void setCustomerId(int customerId){this.customerId = customerId;}

    public String getCustomerName(){return customerName;}

    public void setCustomerName(String customerName){this.customerName = customerName;}

    public String getAddress(){return address;}

    public void setAddress(String address){this.address = address;}

    public int getPostal(){return postal;}

    public void setPostal(int postal){this.postal = postal;}

    public String getPhone(){return phone;}

    public void setPhone(String phone){this.phone = phone;}

    public String getCountry(){return country;}

    public void setCountry(String country){this.country = country;}

    public String getDivision(){return division;}

    public void setDivision(String division){this.division = division;}

    public int getDivisionId(){return divisionId;}

    public void setDivisionId(int divisionId){this.divisionId = divisionId;}


}
