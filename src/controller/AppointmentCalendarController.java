package controller;

import Database.AppointmentQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppointmentCalendarController implements Initializable{


    static ObservableList<Appointments> appointments;

    public TableColumn appID;
    public TableColumn appTitle;
    public TableColumn appDesc;
    public TableColumn appLocation;
    public TableColumn appContact;
    public TableColumn appType;
    public TableColumn appStart;
    public TableColumn appEnd;
    public TableColumn appCusId;
    public TableColumn appUserId;
    public TableView<Appointments> appTableView;
    public TableColumn appContactId;

    public void toAppAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void toAppModify(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Modify Appointment");
        stage.setScene(scene);
        stage.show();

    }

    public void toAppDelete(ActionEvent actionEvent) {
    }

    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}




    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointments = AppointmentQuery.getAllAppointments();

        appTableView.setItems(appointments);

        appID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        appTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        appDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        appLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        appType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        appStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        appEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
        appCusId.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        appUserId.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        appContactId.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));



    }
}
