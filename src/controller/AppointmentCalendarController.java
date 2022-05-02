package controller;

import Database.AppointmentDB;
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
import java.time.*;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppointmentCalendarController implements Initializable {

    //Starting ZoneID and Time
    Locale myLocale = Locale.getDefault();
    LocalDateTime nowDateTime = LocalDateTime.now();

    ZonedDateTime zDateTime = ZonedDateTime.of(nowDateTime, ZoneId.of("US/Central"));
    Locale locEst= new Locale("en", "US/East");

    public Locale getMyLocale() {
        return myLocale;
    }

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void toAppDelete(ActionEvent actionEvent) throws SQLException {
        selectedApp = appTableView.getSelectionModel().getSelectedItem();
        if (selectedApp == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Appointment can NOT be Deleted");
            alert.setContentText("No Appointment Selected");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Are You Sure You Want To Delete?");
            alert.setContentText("Press OK to delete this Appointment. \nPress Cancel to cancel.");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                AppointmentDB.deleteAppointment(selectedApp.getAppointment_ID());
                appTableView.setItems(AppointmentDB.getAllAppointments());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Cancel Appointment");
                alert1.setContentText("Appointment has been canceled");
                alert1.showAndWait();
            }
        }
    }

    public void toMain(ActionEvent actionEvent) throws IOException {
        goToMain(actionEvent);
    }


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainMenu, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointments = AppointmentDB.getAllAppointments();

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

    }


    public void onMonth(ActionEvent actionEvent) throws SQLException {
        if (radioMonth.isSelected()) {
            appointments = AppointmentDB.getMonth();
            appTableView.setItems(appointments);
            appTableView.refresh();
        }
    }

    public void onWeek(ActionEvent actionEvent) throws SQLException {
        if (radioWeek.isSelected()) {
            appointments = AppointmentDB.getWeek();
            appTableView.setItems(appointments);
            appTableView.refresh();
        }
    }

    public void onAll(ActionEvent actionEvent) {
        if (radioAll.isSelected()) {
            appointments = AppointmentDB.getAllAppointments();
            appTableView.setItems(appointments);
            appTableView.refresh();
        }
    }
}
