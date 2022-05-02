package controller;

import Database.ContactQuery;
import Database.CustomerQuery;
import Database.UserQuery;
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
import Database.AppointmentQuery;
import model.Appointments;
import model.Contacts;
import model.Customer;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;

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
    private ComboBox<String> comAddType;
    @FXML
    private ComboBox<Integer> ComCustId;
    @FXML
    private ComboBox<Integer> comUserId;
    @FXML
    private ComboBox<String> addStartHour;
    @FXML
    private DatePicker addStartDate;
    @FXML
    private ComboBox<String> addEndHour;
    @FXML
    private DatePicker addEndDate;


    private ZonedDateTime ESTconversion(LocalDateTime time){
        return ZonedDateTime.of(time, ZoneId.of("US/Eastern"));
    }
   /* private ZonedDateTime UTCconversion(LocalDateTime time){
        return ZonedDateTime.of(time, ZoneId.of(US))
    }*/





    public void onSave(ActionEvent actionEvent) throws IOException {
        boolean valid = appSuccess(
                txtAddTitle.getText(),
                txtAddDesc.getText(),
                txtAddLocation.getText(),
                comAddType.getSelectionModel().getSelectedItem(),
                ComCustId.getSelectionModel().getSelectedItem(),
                comUserId.getSelectionModel().getSelectedItem(),
                addContact.getSelectionModel().getSelectedItem());
        if (valid) {
            try {
                AppointmentQuery.createAppointment(

                        txtAddTitle.getText(),
                        txtAddDesc.getText(),
                        txtAddLocation.getText(),
                        comAddType.getSelectionModel().getSelectedItem(),
                        LocalDateTime.of(addStartDate.getValue(), LocalTime.parse(addStartHour.getSelectionModel().getSelectedItem())),
                        LocalDateTime.of(addEndDate.getValue(), LocalTime.parse(addEndHour.getSelectionModel().getSelectedItem())),
                        ComCustId.getSelectionModel().getSelectedItem(),
                        comUserId.getSelectionModel().getSelectedItem(),
                        addContact.getSelectionModel().getSelectedItem());


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            Parent appointmentCal = FXMLLoader.load(getClass().getResource("/view/AppointmentCalendar.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(appointmentCal, 1000, 450);
            stage.setTitle("Appointment Calendar");
            stage.setScene(scene);
            stage.show();

        }
    }




    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent appointmentCal = FXMLLoader.load(getClass().getResource("/view/AppointmentCalendar.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(appointmentCal, 1000, 450);
        stage.setTitle("Appointment Calendar");
        stage.setScene(scene);
        stage.show();
    }



    private void customerIDBox(){
        ObservableList<Integer> addCustomers = FXCollections.observableArrayList();

        try{
            ObservableList<Customer> allCustomers = CustomerQuery.getCustomer();
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
            ObservableList<Users> allUsers = UserQuery.getAllUsers();
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

        comAddType.setItems(addType);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIDBox();
        userIDBox();
        contactIDBox();
        typeBox();

        LocalTime start = LocalTime.of(8, 0, 0);
        LocalTime end = LocalTime.of(22, 0, 0);

        while(start.isBefore(end.plusSeconds(1))){
            addStartHour.getItems().add(String.valueOf(LocalTime.from(start)));
            start = start.plusMinutes(15);
        }

        LocalTime start1 = LocalTime.of(8, 0, 0);
        LocalTime end1 = LocalTime.of(22, 0, 0);

        while(start1.isBefore(end1.plusSeconds(1))){
            addEndHour.getItems().add(String.valueOf(LocalTime.from(start1)));
            start1 = start1.plusMinutes(15);
        }

        addStartHour.getSelectionModel().select(String.valueOf(LocalTime.of(8, 0)));
        addEndHour.getSelectionModel().select(String.valueOf(LocalTime.of(8, 15)));

    }


    /**Confirms there are no empty fields*/
    private boolean appSuccess(String Title, String Description, String Location, String Type, Integer customerID, Integer userID, String contactName) {
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
        if (comAddType.getValue() == null) {
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
            if (addContact.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Contact Required");
            alert.showAndWait();
            return false;
        }


        /**Checks dates and times are not empty also that start date and time comes before end date and time*/
        if (addStartDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Start Date Required");
            alert.showAndWait();
            return false;
        }
        if (addStartHour.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Start Time Required");
            alert.showAndWait();
            return false;
        }
        if (addEndDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("End Date Required");
            alert.showAndWait();
            return false;
        }
        if (addEndHour.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("End Time Required");
            alert.showAndWait();
            return false;
        }
        if (addEndDate.getValue().isBefore(addStartDate.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Start Date MUST be BEFORE End Date");
            alert.showAndWait();
            return false;
        }

        LocalTime startHour = LocalTime.parse(addStartHour.getSelectionModel().getSelectedItem());
        LocalTime endHour = LocalTime.parse(addEndHour.getSelectionModel().getSelectedItem());

        if (endHour.isBefore(startHour)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Start Time MUST be BEFORE End Time");
            alert.showAndWait();
            return false;
        }

        /**Checks appointment is on one day*/
        LocalDate startDate = addStartDate.getValue();
        LocalDate endDate = addEndDate.getValue();

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

        LocalDateTime start1;
        LocalDateTime end1;

        try{
            ObservableList<Appointments> appointments = AppointmentQuery.getAssocCustomers(ComCustId.getSelectionModel().getSelectedItem());
            assert appointments != null;
            for(Appointments appointments1 : appointments){
                start1 = appointments1.getStartDate().atTime(appointments1.getStart().toLocalTime());
                end1 = appointments1.getEndDate().atTime(appointments1.getEnd().toLocalTime());


                if(pickedStart.isAfter(start1) && pickedStart.isBefore(end1)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Appointments can NOT overlap");
                    alert.showAndWait();
                    return false;
                }
                else if(pickedStart.isBefore(start1) && (pickedEnd.isAfter(end1) || pickedEnd.isEqual(end1))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Appointments can NOT overlap");
                    alert.showAndWait();
                    return false;
                }
                else if(pickedEnd.isAfter(start1) && pickedEnd.isBefore(end1)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Appointments can NOT overlap");
                    alert.showAndWait();
                    return false;
                }
                else if(pickedStart.isEqual(start1) && pickedEnd.isEqual(end1)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Appointments can NOT overlap");
                    alert.showAndWait();
                    return false;
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




        /**Checks that the Appointment is during business hours*/
        ZonedDateTime startConversion = ESTconversion(LocalDateTime.of(addStartDate.getValue(), LocalTime.parse(addStartHour.getSelectionModel().getSelectedItem())));
        ZonedDateTime endConversion = ESTconversion(LocalDateTime.of(addEndDate.getValue(), LocalTime.parse(addEndHour.getSelectionModel().getSelectedItem())));

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
