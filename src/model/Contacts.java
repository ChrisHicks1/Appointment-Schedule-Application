package model;

import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Contacts {
    public int Contact_ID;
    public String Contact_Name;
    public String Email;

    public Contacts(int Contact_ID, String Contact_Name, String Email){
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;
    }

  /*  public Contacts(int Appointment_ID, String Title, String Description, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID) {

    } */


    public int getContact_ID() {
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
