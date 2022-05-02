package controller;

import Database.AppointmentDB;
import Database.ContactDB;
import Database.CustomerDB;
import Database.UserDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customer;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {

    private ZonedDateTime StartDateTimeZone;
    private ZonedDateTime EndDateTimeZone;


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
    private ComboBox<String> modifyType;
    @FXML
    private ComboBox<Integer> ComCustId;
    @FXML
    private ComboBox<Integer> comUserId;
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

    private ZonedDateTime ESTconversion(LocalDateTime time){
        return ZonedDateTime.of(time, ZoneId.of("America/New_York"));
    }


    public void init(Appointments appointments){
        selectedAppIndex = Appointments.getAllAppointments().indexOf(appointments);

        txtModifyAppId.setText(Integer.toString(appointments.getAppointment_ID()));
        txtModifyTitle.setText(appointments.getTitle());
        txtModifyDesc.setText(appointments.getDescription());
        txtModifyLocation.setText(appointments.getLocation());
        modifyType.setValue(appointments.getType());
        modifyContact.setValue(appointments.getContact_Name());
        modifyStartHour.setValue(String.valueOf(appointments.getStart().toLocalTime()));
        modifyStartDate.setValue(appointments.getStartDate());
        modifyEndHour.setValue(String.valueOf(appointments.getEnd().toLocalTime()));
        modifyEndDate.setValue(appointments.getEndDate());
        ComCustId.setValue(appointments.getCustomer_ID());
        comUserId.setValue(appointments.getUser_ID());
    }





    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
            boolean valid = modSuccess(
                    txtModifyTitle.getText(),
                    txtModifyDesc.getText(),
                    txtModifyLocation.getText(),
                    modifyType.getSelectionModel().getSelectedItem(),
                    ComCustId.getSelectionModel().getSelectedItem(),
                    comUserId.getSelectionModel().getSelectedItem(),
                    modifyContact.getSelectionModel().getSelectedItem());
            if (valid) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modify Appointment");
                alert.setContentText("Are you sure you want to Modify this Appointment?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                try {
                    AppointmentDB.modifyAppointment(
                            Integer.parseInt(txtModifyAppId.getText()),
                            txtModifyTitle.getText(),
                            txtModifyDesc.getText(),
                            txtModifyLocation.getText(),
                            modifyType.getSelectionModel().getSelectedItem(),
                            LocalDateTime.of(modifyStartDate.getValue(), LocalTime.parse(modifyStartHour.getSelectionModel().getSelectedItem())),
                            LocalDateTime.of(modifyEndDate.getValue(), LocalTime.parse(modifyEndHour.getSelectionModel().getSelectedItem())),
                            ComCustId.getSelectionModel().getSelectedItem(),
                            comUserId.getSelectionModel().getSelectedItem(),
                            modifyContact.getSelectionModel().getSelectedItem());

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }


                Parent appCal = FXMLLoader.load(getClass().getResource("/view/AppointmentCalendar.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(appCal, 1000, 450);
                stage.setTitle("Appointment Calendar");
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent appCal = FXMLLoader.load(getClass().getResource("/view/AppointmentCalendar.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(appCal, 1000, 450);
        stage.setTitle("Appointment Calendar");
        stage.setScene(scene);
        stage.show();
    }


    private void customerIDBox(){
        ObservableList<Integer> addCustomers = FXCollections.observableArrayList();

        try{
            ObservableList<Customer> allCustomers = CustomerDB.getCustomer();
            for(Customer customer : allCustomers){
                if(!addCustomers.contains(customer.getCustomer_ID())){
                    addCustomers.add(customer.getCustomer_ID());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ComCustId.setItems(addCustomers);
    }

    private void userIDBox(){
        ObservableList<Integer> addUsers = FXCollections.observableArrayList();

        try{
            ObservableList<Users> allUsers = UserDB.getAllUsers();
            for(Users users : allUsers){
                if(!addUsers.contains(users.getUser_ID())){
                    addUsers.add(users.getUser_ID());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        comUserId.setItems(addUsers);
    }

    private void contactIDBox(){
        ObservableList<String> modifyContacts = FXCollections.observableArrayList();

        try {
            ObservableList<Contacts> allContacts = ContactDB.getAllContacts();
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



    public void typeBox() {
        ObservableList<String> addType = FXCollections.observableArrayList();


        addType.add("De-Briefing");
        addType.add("Planning Session");
        addType.add("Hiring Interview 1");
        addType.add("Hiring Interview 2");
        addType.add("Hiring Interview 3");
        addType.add("Exit Interview");
        addType.add("Coffee Break");
        addType.add("Town Hall Meeting");
        addType.add("IT Assistance");

        modifyType.setItems(addType);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIDBox();
        userIDBox();
        contactIDBox();
        typeBox();


        LocalTime start = LocalTime.of(8, 0, 0);
        LocalTime end = LocalTime.of(22, 0, 0);

        while (start.isBefore(end.plusSeconds(1))) {
            modifyStartHour.getItems().add(String.valueOf(LocalTime.from(start)));
            start = start.plusMinutes(15);
        }

        LocalTime start1 = LocalTime.of(8, 0, 0);
        LocalTime end1 = LocalTime.of(22, 0, 0);

        while (start1.isBefore(end1.plusSeconds(1))) {
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

    /**Checks dates and times are not empty also that start date and time comes before end date and time*/
    private boolean modSuccess(String Title, String Description, String Location, String Type, Integer customerID, Integer userID, String contactName){
        if (Title.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Title Required");
            alert.showAndWait();
            return false;
        }
        if (Description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Description Required");
            alert.showAndWait();
            return false;
        }
        if (Location.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Location Required");
            alert.showAndWait();
            return false;
        }
        if (modifyType.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Type Required");
            alert.showAndWait();
            return false;
        }
        if (ComCustId.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Customer Required");
            alert.showAndWait();
            return false;
        }
        if (comUserId.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("User Required");
            alert.showAndWait();
            return false;
        }
        if (modifyContact.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Contact Required");
            alert.showAndWait();
            return false;
        }

        if (modifyStartDate.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Start Date Required");
        alert.showAndWait();
        return false;
    }
        if (modifyStartHour.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Start Time Required");
        alert.showAndWait();
        return false;
    }
        if (modifyEndDate.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("End Date Required");
        alert.showAndWait();
        return false;
    }
        if (modifyEndHour.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("End Time Required");
        alert.showAndWait();
        return false;
    }
        if (modifyEndDate.getValue().isBefore(modifyStartDate.getValue())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Start Date MUST be BEFORE End Date");
        alert.showAndWait();
        return false;
    }

    LocalTime startHour = LocalTime.parse(modifyStartHour.getSelectionModel().getSelectedItem());
    LocalTime endHour = LocalTime.parse(modifyEndHour.getSelectionModel().getSelectedItem());

        if (endHour.isBefore(startHour)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Start Time MUST be BEFORE End Time");
        alert.showAndWait();
        return false;
    }

    /**Checks appointment is on one day*/
    LocalDate startDate = modifyStartDate.getValue();
    LocalDate endDate = modifyEndDate.getValue();

        if (!startDate.equals(endDate)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Appointments can NOT take more than one day");
        alert.showAndWait();
        return false;
    }


    /**Checks Customer appointments do not overlap*/
    LocalDateTime pickedStart = startDate.atTime(startHour);
    LocalDateTime pickedEnd = endDate.atTime(endHour);
    int appID1 = Integer.parseInt(txtModifyAppId.getText());

    LocalDateTime start1;
    LocalDateTime end1;
    int appID;





        try{
        ObservableList<Appointments> appointments = AppointmentDB.getAssocCustomers(ComCustId.getSelectionModel().getSelectedItem());
        for(Appointments appointments1 : appointments){
            start1 = appointments1.getStartDate().atTime(appointments1.getStart().toLocalTime());
            end1 = appointments1.getEndDate().atTime(appointments1.getEnd().toLocalTime());
            appID = appointments1.getAppointment_ID();

            if(pickedStart.isAfter(start1) && pickedStart.isBefore(end1)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Appointments can NOT start before the end of another appointment.");
                alert.showAndWait();
                return false;
            }
            else if(pickedStart.isBefore(start1) && (pickedEnd.isAfter(end1) || pickedEnd.isEqual(end1))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Appointments can NOT end after the start of another appointment.");
                alert.showAndWait();
                return false;
            }
            else if(pickedEnd.isAfter(start1) && pickedEnd.isBefore(end1)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Appointments can NOT be scheduled during another appointment.");
                alert.showAndWait();
                return false;
            }
            else if(pickedStart.isEqual(start1) && pickedEnd.isEqual(end1)) {
                if (appID1 != (appID)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Appointments can NOT be scheduled for the same time.");
                    alert.showAndWait();
                    return false;
                } else {
                    return true;
                }
            }
            else if(pickedStart.isEqual(pickedEnd)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Appointment time must be at least 15 minutes");
                alert.showAndWait();
                return false;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }




    /**Checks that the Appointment is during business hours and not on the Weekend*/
    ZonedDateTime startConversion = ESTconversion(LocalDateTime.of(modifyStartDate.getValue(), LocalTime.parse(modifyStartHour.getSelectionModel().getSelectedItem())));
    ZonedDateTime endConversion = ESTconversion(LocalDateTime.of(modifyEndDate.getValue(), LocalTime.parse(modifyEndHour.getSelectionModel().getSelectedItem())));

        if(startConversion.toLocalTime().isAfter(LocalTime.of(22,0))){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Business hours are between 8AM and 10PM EST");
        alert.showAndWait();
        return false;
    }
        if(startConversion.toLocalTime().isBefore(LocalTime.of(8, 0))){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Business hours are between 8AM and 10PM EST");
        alert.showAndWait();
        return false;
    }

        if(endConversion.toLocalTime().isAfter(LocalTime.of(22,0))){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Business hours are between 8AM and 10PM EST");
        alert.showAndWait();
        return false;
    }

        if(endConversion.toLocalTime().isBefore(LocalTime.of(8,0))){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Business hours are between 8AM and 10PM EST");
        alert.showAndWait();
        return false;
    }

        return true;
}



}
