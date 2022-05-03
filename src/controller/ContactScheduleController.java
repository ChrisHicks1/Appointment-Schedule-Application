package controller;

import Database.AppointmentDB;
import Database.ContactDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactScheduleController implements Initializable {


    private static ObservableList<Appointments> appointments = FXCollections.observableArrayList();


    @FXML
    private TableView<Appointments> contactTableView;
    @FXML
    private TableColumn appCol;
    @FXML
    private TableColumn titleCol;
    @FXML
    private TableColumn descCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn startCol;
    @FXML
    private TableColumn endCol;
    @FXML
    private TableColumn customerCol;
    @FXML
    private ComboBox<String> comContact;

    /**Returns to Main Menu on Back button*/
    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}

    /**Helper that returns to Main Menu*/
    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainMenu, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


    /**Updates table view when Selecting a contact*/
    public void onCombo(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> appointments = AppointmentDB.getAssocContacts(comContact.getSelectionModel().getSelectedItem());
        contactTableView.setItems(appointments);
        contactTableView.refresh();

    }


    /**Populates contact comboBox*/
    private void contactName(){
        ObservableList<String> addContacts = FXCollections.observableArrayList();

        try {
            ObservableList<Contacts> allContacts = ContactDB.getAllContacts();
            for(Contacts contacts: allContacts){
                if(!addContacts.contains(contacts.getContact_Name())){
                    addContacts.add(contacts.getContact_Name());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comContact.setItems(addContacts);

    }


    /**Initializes Contact ComboBox and Contact table view*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactName();


        appointments = AppointmentDB.getAllAppointments();


        appCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        contactTableView.setItems(appointments);

    }
}

