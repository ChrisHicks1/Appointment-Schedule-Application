package controller;

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
import java.time.LocalTime;
import java.util.ResourceBundle;

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
    private ComboBox<Contacts> modifyContact;
    @FXML
    private TextField txtModifyType;
    @FXML
    private TextField txtModifyCusId;
    @FXML
    private TextField txtModifyUserId;
    @FXML
    private ComboBox modifyStartHour;
    @FXML
    private DatePicker modifyStartDate;
    @FXML
    private ComboBox modifyEndHour;
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
     //   modifyContact.setValue(appointments.getContact_Name(allContacts));
        txtModifyType.setText(appointments.getType());
      //  modifyStartDate.setDayCellFactory();
      //  modifyStartHour.setItems(appointments.getStart());

     //   modifyEndDate;
    //    modifyEndHour;
      //  modifyEndMin;
        txtModifyCusId.setText(Integer.toString(appointments.getCustomer_ID()));
        txtModifyUserId.setText(Integer.toString(appointments.getUser_ID()));
        txtModifyContactId.setText(Integer.toString(appointments.getContact_ID()));
    }





    public void onSave(ActionEvent actionEvent) {
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

        LocalTime start = LocalTime.of(6, 0);
        LocalTime end = LocalTime.of(16, 45);

        while(start.isBefore(end.plusSeconds(1))){
            modifyStartHour.getItems().add(start);
            start = start.plusMinutes(15);
        }
        modifyStartHour.getSelectionModel().select(LocalTime.of(6, 0));

        LocalTime start1 = LocalTime.of(6, 15);
        LocalTime end1 = LocalTime.of(17, 0);

        while(start1.isBefore(end1.plusSeconds(1))){
            modifyEndHour.getItems().add(start1);
            start1 = start1.plusMinutes(15);
        }
        modifyEndHour.getSelectionModel().select(LocalTime.of(6, 15));

    }
}
