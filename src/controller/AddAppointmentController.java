package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Database.AppointmentQuery;

import java.io.IOException;

public class AddAppointmentController {
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
    private ComboBox addContact;
    @FXML
    private TextField addTextType;
    @FXML
    private TextField addTextCustId;
    @FXML
    private TextField addTextUserId;
    @FXML
    private ComboBox addStartHour;
    @FXML
    private ComboBox addStartMin;
    @FXML
    private DatePicker addStartDate;
    @FXML
    private ComboBox addEndHour;
    @FXML
    private ComboBox addEndMin;
    @FXML
    private DatePicker addEndDate;

    public void onSave(ActionEvent actionEvent) throws IOException{
        String Appointment_ID = txtAddAppId.getId();
        String Title = txtAddTitle.getText();
        String Description = txtAddDesc.getText();
        String Location = txtAddLocation.getText();
        String Type = addTextType.getText();

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
}
