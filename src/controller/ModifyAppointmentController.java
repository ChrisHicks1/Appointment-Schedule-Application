package controller;

import Database.AppointmentQuery;
import Database.ContactQuery;
import javafx.collections.FXCollections;
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
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
    private ComboBox<String> modifyContact;
    @FXML
    private TextField txtModifyType;
    @FXML
    private TextField txtModifyCusId;
    @FXML
    private TextField txtModifyUserId;
    @FXML
    private ComboBox<String> modifyStartHour;
    @FXML
    private DatePicker modifyStartDate;
    @FXML
    private ComboBox<String> modifyEndHour;
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
        txtModifyType.setText(appointments.getType());
        modifyContact.setValue(appointments.getContact_Name());
        modifyStartHour.setValue(String.valueOf(appointments.getStart().toLocalTime()));
        modifyStartDate.setValue(appointments.getStartDate());
        modifyEndHour.setValue(String.valueOf(appointments.getEnd().toLocalTime()));
        modifyEndDate.setValue(appointments.getEndDate());
        txtModifyCusId.setText(Integer.toString(appointments.getCustomer_ID()));
        txtModifyUserId.setText(Integer.toString(appointments.getUser_ID()));
    }





    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        try {
            AppointmentQuery.modifyAppointment(
                    Integer.parseInt(txtModifyAppId.getText()),
                    txtModifyTitle.getText(),
                    txtModifyDesc.getText(),
                    txtModifyLocation.getText(),
                    txtModifyType.getText(),
                    LocalDateTime.of(modifyStartDate.getValue(), LocalTime.parse(modifyStartHour.getSelectionModel().getSelectedItem())),
                    LocalDateTime.of(modifyEndDate.getValue(), LocalTime.parse(modifyEndHour.getSelectionModel().getSelectedItem())),
                    Integer.parseInt(txtModifyCusId.getText()),
                    Integer.parseInt(txtModifyUserId.getText()),
                    modifyContact.getSelectionModel().getSelectedItem());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/AppointmentCalendar.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 1000, 450);
        stage.setTitle("Appointment Calendar");
        stage.setScene(scene);
        stage.show();
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

    private void contactIDBox(){
        ObservableList<String> modifyContacts = FXCollections.observableArrayList();

        try {
            ObservableList<Contacts> allContacts = ContactQuery.getAllContacts();
                for(Contacts contacts: allContacts){
                    if(!modifyContacts.contains(contacts.getContact_Name())){
                        modifyContacts.add(contacts.getContact_Name());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        modifyContact.setItems(modifyContacts);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactIDBox();



        LocalTime start = LocalTime.of(6, 0, 0);
        LocalTime end = LocalTime.of(16, 45, 0);

        while(start.isBefore(end.plusSeconds(1))){
            modifyStartHour.getItems().add(String.valueOf(LocalTime.from(start)));
            start = start.plusMinutes(15);
        }

        LocalTime start1 = LocalTime.of(6, 15, 0);
        LocalTime end1 = LocalTime.of(17, 0, 0);

        while(start1.isBefore(end1.plusSeconds(1))){
            modifyEndHour.getItems().add(String.valueOf(LocalTime.from(start1)));
            start1 = start1.plusMinutes(15);
        }

        modifyStartHour.getSelectionModel().select(String.valueOf(start));
        modifyEndHour.getSelectionModel().select(String.valueOf(start1));


    }


    public void onStartDate(ActionEvent actionEvent) {
    }

    public void onEndDate(ActionEvent actionEvent) {

    }
}
