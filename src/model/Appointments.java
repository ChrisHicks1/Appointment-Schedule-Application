package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointments {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Contact_Name;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;


    public Appointments(int Appointment_ID, String Title, String Description, String Location, String Contact_Name, String Type, LocalDateTime Start, LocalDateTime End, int Customer_ID, int User_ID, int Contact_ID){
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Contact_Name = Contact_Name;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;

    }


    public int getAppointment_ID(){return Appointment_ID;}
    public void setAppointment_ID(int Appointment_ID){this.Appointment_ID = Appointment_ID;}


    public String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }


    public String getDescription(){return Description;}
    public void setDescription(String Description) {
        this.Description = Description;
    }


    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }


    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String Contact_Name) {
        this.Contact_Name = Contact_Name;
    }

    public String getType() {
        return Type;
    }
    public void setType(String Type) {
        this.Type = Type;
    }


    public LocalDateTime getStart() {
        return Start;
    }
    public void setStart(LocalDateTime Start) {
        this.Start = Start;
    }


    public LocalDateTime getEnd() {
        return End;
    }
    public void setEnd(LocalDateTime End) {
        this.End = End;
    }


    public int getCustomer_ID() {
        return Customer_ID;
    }
    public void setCustomer_ID(){
        this.Customer_ID = Customer_ID;
    }


    public int getUser_ID() {
        return User_ID;
    }
    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }


    public int getContact_ID() {
        return Contact_ID;
    }
    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }

}
