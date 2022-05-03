package controller;

import Database.AppointmentDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class AppointmentCalendarController implements Initializable {


    static ObservableList<Appointments> appointments;

    @FXML
    private TableColumn appID;
    @FXML
    private TableColumn appTitle;
    @FXML
    private TableColumn appDesc;
    @FXML
    private TableColumn appLocation;
    @FXML
    private TableColumn appContact;
    @FXML
    private TableColumn appType;
    @FXML
    private TableColumn appStart;
    @FXML
    private TableColumn appEnd;
    @FXML
    private TableColumn appCusId;
    @FXML
    private TableColumn appUserId;
    @FXML
    private TableView<Appointments> appTableView;
    @FXML
    private RadioButton radioMonth;
    @FXML
    private ToggleGroup tGroup;
    @FXML
    private RadioButton radioWeek;
    @FXML
    private RadioButton radioAll;

    private static Appointments selectedApp;
    private static int selectedAppIndex;


    /**Goes to Add Appointment Screen*/
    public void toAppAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }


    /**Checks that Appointment is selected, then goes to Modify Appointment screen and passes appointment data*/
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


    /**Checks that Appointment is selected, confirms delete, then removes appointment from table*/
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

    /**Returns to Main Menu on Back button*/
    public void toMain(ActionEvent actionEvent) throws IOException {
        goToMain(actionEvent);
    }

    /**Helper to return to Main Menu*/
    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainMenu, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


    /**Initializes table view*/
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


    /**When Month Appointments is selected, shows all appointments this upcoming Month*/
    public void onMonth(ActionEvent actionEvent) throws SQLException {
        if (radioMonth.isSelected()) {
            appointments = AppointmentDB.getMonth();
            appTableView.setItems(appointments);
            appTableView.refresh();
        }
    }


    /**When Week Appointments is selected, shows all appointments this upcoming week*/
    public void onWeek(ActionEvent actionEvent) throws SQLException {
        if (radioWeek.isSelected()) {
            appointments = AppointmentDB.getWeek();
            appTableView.setItems(appointments);
            appTableView.refresh();
        }
    }

    /**When All is selected, shows all appointments*/
    public void onAll(ActionEvent actionEvent) {
        if (radioAll.isSelected()) {
            appointments = AppointmentDB.getAllAppointments();
            appTableView.setItems(appointments);
            appTableView.refresh();
        }
    }
}
