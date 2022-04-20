package controller;

import Database.AppointmentQuery;
import Database.ContactQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class ModifyAppointmentController implements Initializable {
    @FXML
    private TextField txtModifyContactId;
    @FXML
    private Button modifySave;
    @FXML
    private TextField txtModifyAppId;
    @FXML
    private TextField txtModifyTitle;
    @FXML
    private TextField txtModifyDesc;
    @FXML
    private TextField txtModifyLocation;
    @FXML
    private ComboBox modifyContact;
    @FXML
    private TextField txtModifyType;
    @FXML
    private TextField txtModifyCusId;
    @FXML
    private TextField txtModifyUserId;
    @FXML
    private ComboBox<LocalTime> modifyStartHour;
    @FXML
    private DatePicker modifyStartDate;
    @FXML
    private ComboBox<LocalTime> modifyEndHour;
    @FXML
    private DatePicker modifyEndDate;

    public static Appointments selectedApp;
    public int selectedAppIndex;





    public void init(Appointments appointments){
        selectedAppIndex = Appointments.getAllAppointments().indexOf(appointments);


        txtModifyAppId.setText(Integer.toString(appointments.getAppointment_ID()));
        txtModifyTitle.setText(appointments.getTitle());
        txtModifyDesc.setText(appointments.getDescription());
        txtModifyLocation.setText(appointments.getLocation());
        modifyContact.setValue(appointments.getContact_Name());
        txtModifyType.setText(appointments.getType());

        modifyStartHour.setValue(appointments.getStart().toLocalTime());
        modifyStartDate.setValue(appointments.getStartDate());
        modifyEndHour.setValue(appointments.getEnd().toLocalTime());
        modifyEndDate.setValue(appointments.getEndDate());

        txtModifyCusId.setText(Integer.toString(appointments.getCustomer_ID()));
        txtModifyUserId.setText(Integer.toString(appointments.getUser_ID()));
        txtModifyContactId.setText(Integer.toString(appointments.getContact_ID()));
    }





    public void onSave(ActionEvent actionEvent) throws SQLException {
        int Appointment_ID = Integer.parseInt(txtModifyAppId.getId());
        String Title = txtModifyTitle.getText();
        String Description = txtModifyDesc.getText();
        String Location = txtModifyLocation.getText();
        String Contact_Name = modifyContact.getId();
        String Type = txtModifyType.getText();
        LocalTime Start = modifyStartHour.getValue();
        LocalDate startDate = modifyStartDate.getValue();
        LocalTime End = modifyEndHour.getValue();
        LocalDate endDate = modifyEndDate.getValue();
        int Customer_ID = Integer.parseInt(txtModifyCusId.getText());
        int User_ID = Integer.parseInt(txtModifyUserId.getId());
        int Contact_ID = Integer.parseInt(txtModifyContactId.getId());

        AppointmentQuery.modifyAppointment(Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, startDate, End, endDate, Customer_ID, User_ID, Contact_ID);
    }

    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/AppointmentCalendar.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 1000, 450);
        stage.setTitle("Appointment Calendar");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        ObservableList<Contacts> allContacts = ContactQuery.getAllContacts();

        modifyContact.setItems(allContacts);





        LocalTime start = LocalTime.of(5, 0);
        LocalTime end = LocalTime.of(16, 45);

        while(start.isBefore(end.plusSeconds(1))){
            modifyStartHour.getItems().add(LocalTime.from(start));
            start = start.plusMinutes(15);
        }

        LocalTime start1 = LocalTime.of(5, 15);
        LocalTime end1 = LocalTime.of(17, 0);

        while(start1.isBefore(end1.plusSeconds(1))){
            modifyEndHour.getItems().add(LocalTime.from(start1));
            start1 = start1.plusMinutes(15);
        }

        modifyStartHour.getSelectionModel().select(start);
        modifyEndHour.getSelectionModel().select(start1);

/*
        LocalTime start1 = modifyStartHour.getValue();
        LocalTime end1 = modifyEndHour.getValue();

        while(start1.isBefore(end1.plusSeconds(1))){
            modifyEndHour.getItems().add(LocalTime.from(start1));
            start1 = start1.plusMinutes(15);
        }
*/

    }

    public void onStartDate(ActionEvent actionEvent) {
    }

    public void onEndDate(ActionEvent actionEvent) {
    }
}
