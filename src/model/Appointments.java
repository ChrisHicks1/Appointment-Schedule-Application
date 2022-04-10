package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private int userId;
    private int contactId;
    private String contactName;

    public Appointments(int appointmentId, String title, String description, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId, String contactName){
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this. type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    public int getAppointmentId(){return appointmentId;}
    public void setAppointmentId(){this.appointmentId = appointmentId;}


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription(){return description;}
    public void setDescription(String description) {
        this.description = description;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(){
        this.customerId = customerId;
    }


    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getContactId() {
        return contactId;
    }
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }


    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
