package controller;

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
import Database.AppointmentQuery;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class AddAppointmentController implements Initializable {
    @FXML
    private TextField addTextContactId;
    @FXML
    private Button addSave;
    @FXML
    private TextField txtAddAppId;
    @FXML
    private TextField txtAddTitle;
    @FXML
    private TextField txtAddDesc;
    @FXML
    private TextField txtAddLocation;
    @FXML
    private ComboBox<String> addContact;
    @FXML
    private TextField addTextType;
    @FXML
    private TextField addTextCustId;
    @FXML
    private TextField addTextUserId;
    @FXML
    private ComboBox<String> addStartHour;
    @FXML
    private DatePicker addStartDate;
    @FXML
    private ComboBox<String> addEndHour;
    @FXML
    private DatePicker addEndDate;

    public void onSave(ActionEvent actionEvent) throws IOException {


        try {
            AppointmentQuery.createAppointment(

                    txtAddTitle.getText(),
                    txtAddDesc.getText(),
                    txtAddLocation.getText(),
                    addTextType.getText(),
                    LocalDateTime.of(addStartDate.getValue(), LocalTime.parse(addStartHour.getSelectionModel().getSelectedItem())),
                    LocalDateTime.of(addEndDate.getValue(), LocalTime.parse(addEndHour.getSelectionModel().getSelectedItem())),
                    Integer.parseInt(addTextCustId.getText()),
                    Integer.parseInt(addTextUserId.getText()),
                    addContact.getSelectionModel().getSelectedItem());


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
        ObservableList<String> addContacts = FXCollections.observableArrayList();

        try {
            ObservableList<Contacts> allContacts = ContactQuery.getAllContacts();
                for(Contacts contacts: allContacts){
                    if(!addContacts.contains(contacts.getContact_Name())){
                        addContacts.add(contacts.getContact_Name());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            addContact.setItems(addContacts);

    }


    //check for empty
    //check for overlap
    //timezone



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactIDBox();

        LocalTime start = LocalTime.of(6, 0);
        LocalTime end = LocalTime.of(16, 45);

        while(start.isBefore(end.plusSeconds(1))){
            addStartHour.getItems().add(String.valueOf(start));
            start = start.plusMinutes(15);
        }
        addStartHour.getSelectionModel().select(String.valueOf(LocalTime.of(6, 0)));

        LocalTime start1 = LocalTime.of(6, 15);
        LocalTime end1 = LocalTime.of(17, 0);

        while(start1.isBefore(end1.plusSeconds(1))){
            addEndHour.getItems().add(String.valueOf(start1));
            start1 = start1.plusMinutes(15);
        }
        addEndHour.getSelectionModel().select(String.valueOf(LocalTime.of(6, 15)));

    }

}
