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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactScheduleController implements Initializable {


    private static ObservableList<Appointments> appointments = FXCollections.observableArrayList();;
    static ObservableList<Contacts> contacts = FXCollections.observableArrayList();

    @FXML
    public TableView<Appointments> contactTableView;
    @FXML
    public TableColumn appCol;
    @FXML
    public TableColumn titleCol;
    @FXML
    public TableColumn descCol;
    @FXML
    public TableColumn typeCol;
    @FXML
    public TableColumn startCol;
    @FXML
    public TableColumn endCol;
    @FXML
    public TableColumn customerCol;
    @FXML
    public ComboBox<Contacts> comContact;

    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}




    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


    public void onCombo(ActionEvent actionEvent) {
        Contacts selcon = comContact.getSelectionModel().getSelectedItem();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Contacts> allContacts = ContactQuery.getAllContacts();


        comContact.setItems(allContacts);
        comContact.setPromptText("Select Contact");

        /*
        contactTableView.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                for(int i = 0; i < comContact.getItems().size(); i++){
                    Contacts abp = comContact.getItems().get(i);
                    if(abp.getContact_ID() == newSelection.getContact_ID() && abp.getContact_Name().equals(newSelection.getContact_Name())){
                        comContact.setValue(abp);
                        break;
                    }
                }
            }
        }); */


        appointments = AppointmentQuery.getAllAppointments();


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

