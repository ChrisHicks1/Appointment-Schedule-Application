package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointments {
    private int Appointment_Id;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private int Customer_Id;
    private int User_Id;
    private int Contact_Id;


    public Appointments(int Appointment_Id, String Title, String Description, String Location, String Type, LocalDateTime Start, LocalDateTime End, int Customer_Id, int User_Id, int Contact_Id){
        this.Appointment_Id = Appointment_Id;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_Id = Customer_Id;
        this.User_Id = User_Id;
        this.Contact_Id = Contact_Id;

    }


    public int getAppointment_Id(){return Appointment_Id;}
    public void setAppointment_Id(){this.Appointment_Id = Appointment_Id;}


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


    public int getCustomer_Id() {
        return Customer_Id;
    }
    public void setCustomer_Id(){
        this.Customer_Id = Customer_Id;
    }


    public int getUser_Id() {
        return User_Id;
    }
    public void setUser_Id(int userId) {
        this.User_Id = User_Id;
    }


    public int getContact_Id() {
        return Contact_Id;
    }
    public void setContact_Id(int Contact_Id) {
        this.Contact_Id = Contact_Id;
    }

}
