package model;

import Database.AppointmentQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointments> associatedAppointment = FXCollections.observableArrayList();


    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private String Country;
    private String Division;
    private int Division_ID;

    public Customer(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, String Country, String Division, int Division_ID){
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Country = Country;
        this.Division = Division;
        this.Division_ID = Division_ID;
    }


    public int getCustomer_ID(){return Customer_ID;}

    public void setCustomer_ID(int Customer_ID){this.Customer_ID = Customer_ID;}

    public String getCustomer_Name(){return Customer_Name;}

    public void setCustomer_Name(String Customer_Name){this.Customer_Name = Customer_Name;}

    public String getAddress(){return Address;}

    public void setAddress(String Address){this.Address = Address;}

    public String getPostal_Code(){return Postal_Code;}

    public void setPostal_Code(String Postal_Code){this.Postal_Code = Postal_Code;}

    public String getPhone(){return Phone;}

    public void setPhone(String Phone){this.Phone = Phone;}

    public String getCountry(){return Country;}

    public void setCountry(String Country){this.Country = Country;}

    public String getDivision(){return Division;}

    public void setDivision(String Division){this.Division = Division;}

    public int getDivision_ID(){return Division_ID;}

    public void setDivision_ID(int Division_ID){this.Division_ID = Division_ID;}

    public static ObservableList<Customer> getAllCustomers(){ return allCustomers; }

    public static ObservableList<Appointments> getAssociatedAppointment(){ return  associatedAppointment; }


}
