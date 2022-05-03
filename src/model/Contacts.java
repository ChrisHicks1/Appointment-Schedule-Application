package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacts {

    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    public int Contact_ID;
    public String Contact_Name;
    public String Email;


    /**Contacts Constructor*/
    public Contacts(int Contact_ID, String Contact_Name, String Email){
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;
    }



    /**Contacts Getters and Setters*/
    public Integer getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }


    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String Contact_Name) {
        this.Contact_Name = Contact_Name;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
