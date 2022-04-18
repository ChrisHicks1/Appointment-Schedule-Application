package controller;

import Database.AppointmentQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Customer;


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
    public RadioButton radioMonth;
    public ToggleGroup tGroup;
    public RadioButton radioWeek;
    public RadioButton radioAll;

    private static Appointments selectedApp;
    private static int selectedAppIndex;



    public void toAppAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void toAppModify(ActionEvent actionEvent) throws IOException {

        selectedApp = appTableView.getSelectionModel().getSelectedItem();
        selectedAppIndex = Customer.getAllCustomers().indexOf(selectedApp);
        if (selectedApp == null) {
            Alert nullAlert = new Alert(Alert.AlertType.ERROR);
            nullAlert.setTitle("Error");
            nullAlert.setHeaderText("Appointment can NOT be Modified");
            nullAlert.setContentText("No Appointment Selected");
            nullAlert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyAppointment.fxml"));
                Parent modifyApp = loader.load();
                ModifyAppointmentController modifiedApp = loader.getController();
                modifiedApp.init(selectedApp);

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(modifyApp, 450, 550);
                stage.setTitle("Modify Appointment");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
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
        appContact.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
        appType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        appStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        appEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
        appCusId.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        appUserId.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        appContactId.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));



    }

    public void onMonth(ActionEvent actionEvent) {
    }

    public void onWeek(ActionEvent actionEvent) {
    }

    public void onAll(ActionEvent actionEvent) {
    }
}
