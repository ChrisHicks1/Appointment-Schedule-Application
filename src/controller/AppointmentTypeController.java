package controller;

import Database.AppointmentQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentTypeController implements Initializable {

    static ObservableList<Appointments> appointments = FXCollections.observableArrayList();


    @FXML
    private ComboBox<String> comMonth;
    @FXML
    private ComboBox<String> comType;
    @FXML
    private Label monthType;




    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}



    public interface typeAmountInterface{
        void amountCount(String i);
    }


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainMenu, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }





    typeAmountInterface amount = i -> System.out.println("Some" + i); {

        amount.amountCount("thing");

    }


    public void typeBox(){
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

        comType.setItems(addType);

      /*  ObservableList<Appointments> noTypes = AppointmentQuery.getAllAppointments();
        assert noTypes != null;
        for(Appointments appointments : noTypes){
            addType.add(appointments.getType());

    }
        comType.setItems(addType);*/
    }

    @FXML
    private void onComType(ActionEvent actionEvent){
        ObservableList<String> tlist = FXCollections.observableArrayList();
        try{
            ObservableList<Appointments> appointments = AppointmentQuery.getAllTypes(comMonth.getSelectionModel().getSelectedItem());

            assert appointments != null;
            for (Appointments appointments1 : appointments) {
                tlist.add(appointments1.getType());
            }
            comType.setItems(tlist);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onComMonth(ActionEvent actionEvent) {
        ObservableList<String> addMonth = FXCollections.observableArrayList();
        try{
            ObservableList<Appointments> appointments = AppointmentQuery.getAllTypes(comType.getSelectionModel().getSelectedItem());
            ObservableList<Appointments> appointments1 = FXCollections.observableArrayList();

            for(Appointments appointments2 : appointments) {
                LocalDate start = appointments2.getStartDate();

                for (String month : addMonth) {
                    addMonth.add(appointments.indexOf(0), month);
                }
                //comType.setItems();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        // ObservableList<String> January = FXCollections.observableArrayList();

       // addMonth.add(January.size(), String.valueOf(January));

       /* ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        for(Appointments appointments1 : appointments) {
            LocalDate start = appointments1.getStartDate();
                if(start.getMonth().equals(Month.JANUARY)) {
                    month.add(String.valueOf(start.getMonthValue()));
                    //comMonth.setItems(January);
                }


        }
*/

     /*   ObservableList<Appointments> appointments = AppointmentQuery.getAllAppointments();


        for (Appointments appointments1 : appointments) {
            LocalDate start = appointments1.getStartDate();
            if (start.getMonth().equals(Month.JANUARY)) {
                January.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.FEBRUARY)) {
                February.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.MARCH)) {
                March.add(String.valueOf(start.getMonthValue()));
            }
            */
    }

    private void monthBox(){
      /*  ObservableList<String> month = FXCollections.observableArrayList();

       // month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");

        comMonth.setItems(month);*/

        ObservableList<String> addMonth = FXCollections.observableArrayList();
        addMonth.add(0, "January");
        addMonth.add(1, "February");
        addMonth.add(2, "March");
        addMonth.add(3, "April");
        addMonth.add(4, "May");
        addMonth.add(5, "June");
        addMonth.add(6, "July");
        addMonth.add(7, "August");
        addMonth.add(8, "September");
        addMonth.add(9, "October");
        addMonth.add(10, "November");
        addMonth.add(11, "December");

        ObservableList<String> January = FXCollections.observableArrayList();
        // addMonth.add(String.valueOf(January));


        comMonth.setItems(addMonth);

        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        for(Appointments appointments1 : appointments) {
            LocalDate start = appointments1.getStartDate();
            String Type = appointments1.getType();
            if(start.getMonth().equals(Month.valueOf("January"))) {
                addMonth.add(String.valueOf(start.getMonthValue()));


            }


        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        typeBox();
        monthBox();
        appointments = AppointmentQuery.getAllAppointments();

        monthType.setText(appointments.size() + " Appointments Scheduled");

    }




    private void amountMonth() {


            }






}

