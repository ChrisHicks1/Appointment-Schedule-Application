package controller;

import Database.AppointmentQuery;
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


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

public class AppointmentTypeController implements Initializable {

    static ObservableList<Appointments> appointments = FXCollections.observableArrayList();


    @FXML
    private ComboBox<String> comMonth;
    @FXML
    private ComboBox<String> comType;
    @FXML
    private Label monthCount;
    @FXML
    private Label typeCount;




    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainMenu, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }



    public interface monthAmountInterface{
        void countMonth(String i);
    }

    public interface typeAmountInterface{
        void countType(String i);
    }




    public void typeBox(){
        ObservableList<String> addType = FXCollections.observableArrayList();

        addType.add(0, "De-Briefing");
        addType.add(1, "Planning Session");
        addType.add(2, "Hiring Interview 1");
        addType.add(3, "Hiring Interview 2");
        addType.add(4, "Hiring Interview 3");
        addType.add(5, "Exit Interview");
        addType.add(6, "Coffee Break");
        addType.add(7, "Town Hall Meeting");
        addType.add(8, "IT Assistance");

        comType.setItems(addType);
    }

    @FXML
    private void onComType(ActionEvent actionEvent){
        ObservableList<String> deBriefing = FXCollections.observableArrayList();
        ObservableList<String> planningSession = FXCollections.observableArrayList();
        ObservableList<String> interview1 = FXCollections.observableArrayList();
        ObservableList<String> interview2 = FXCollections.observableArrayList();
        ObservableList<String> interview3 = FXCollections.observableArrayList();
        ObservableList<String> exitInterview = FXCollections.observableArrayList();
        ObservableList<String> coffee = FXCollections.observableArrayList();
        ObservableList<String> townHall = FXCollections.observableArrayList();
        ObservableList<String> itAssist = FXCollections.observableArrayList();

        try {

            for (Appointments appointments1 : appointments) {
                String type = appointments1.getType();

                if (type.equals("De-Briefing")) {
                    deBriefing.add(type);
                }
                if (type.equals("Planning Session")) {
                    planningSession.add(type);
                }
                if (type.equals("Hiring Interview 1")) {
                    interview1.add(type);
                }
                if (type.equals("Hiring Interview 2")) {
                    interview2.add(type);
                }
                if (type.equals("Hiring Interview 3")) {
                    interview3.add(type);
                }
                if (type.equals("Exit Interview")) {
                    exitInterview.add(type);
                }
                if (type.equals("Coffee Break")) {
                    coffee.add(type);
                }
                if (type.equals("Town Hall Meeting")) {
                    townHall.add(type);
                }
                if (type.equals("IT Assistance")) {
                    itAssist.add(type);
                }


                if(comType.getSelectionModel().isSelected(0)){
                    typeCount.setText(String.valueOf(deBriefing.size()));
                }
                if(comType.getSelectionModel().isSelected(1)){
                    typeCount.setText(String.valueOf(planningSession.size()));
                }
                if(comType.getSelectionModel().isSelected(2)){
                    typeCount.setText(String.valueOf(interview1.size()));
                }
                if(comType.getSelectionModel().isSelected(3)){
                    typeCount.setText(String.valueOf(interview2.size()));
                }
                if(comType.getSelectionModel().isSelected(4)){
                    typeCount.setText(String.valueOf(interview3.size()));
                }
                if(comType.getSelectionModel().isSelected(5)){
                    typeCount.setText(String.valueOf(exitInterview.size()));
                }
                if(comType.getSelectionModel().isSelected(6)){
                    typeCount.setText(String.valueOf(coffee.size()));
                }
                if(comType.getSelectionModel().isSelected(7)){
                    typeCount.setText(String.valueOf(townHall.size()));
                }
                if(comType.getSelectionModel().isSelected(8)){
                    typeCount.setText(String.valueOf(itAssist.size()));
                }

                typeAmountInterface amount = i -> typeCount.setText(typeCount.getText() + i);
                {
                    if (Integer.parseInt(typeCount.getText()) == 1) {
                        amount.countType(" appointment of this Type.");
                    } else {
                        amount.countType(" appointments of this Type.");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onComMonth(ActionEvent actionEvent) {
        ObservableList<String> January = FXCollections.observableArrayList();
        ObservableList<String> February = FXCollections.observableArrayList();
        ObservableList<String> March = FXCollections.observableArrayList();
        ObservableList<String> April = FXCollections.observableArrayList();
        ObservableList<String> May = FXCollections.observableArrayList();
        ObservableList<String> June = FXCollections.observableArrayList();
        ObservableList<String> July = FXCollections.observableArrayList();
        ObservableList<String> August = FXCollections.observableArrayList();
        ObservableList<String> September = FXCollections.observableArrayList();
        ObservableList<String> October = FXCollections.observableArrayList();
        ObservableList<String> November = FXCollections.observableArrayList();
        ObservableList<String> December = FXCollections.observableArrayList();

        for (Appointments appointments1 : appointments) {
            LocalDateTime start = appointments1.getStart();

            if (start.getMonth().equals(Month.JANUARY)) {
                January.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.FEBRUARY)) {
                February.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.MARCH)) {
                March.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.APRIL)) {
                April.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.MAY)) {
                May.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.JUNE)) {
                June.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.JULY)) {
                July.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.AUGUST)) {
                August.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.SEPTEMBER)) {
                September.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.OCTOBER)) {
                October.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.NOVEMBER)) {
                November.add(String.valueOf(start.getMonthValue()));
            }
            if (start.getMonth().equals(Month.DECEMBER)) {
                December.add(String.valueOf(start.getMonthValue()));
            }


            if (start.getMonth().equals(Month.JANUARY)) {
                January.add(String.valueOf(start.getMonthValue()));
            }
            if (comMonth.getSelectionModel().isSelected(0)) {
                monthCount.setText(String.valueOf(January.size()));
            }
            if (comMonth.getSelectionModel().isSelected(1)) {
                monthCount.setText(String.valueOf(February.size()));
            }
            if (comMonth.getSelectionModel().isSelected(2)) {
                monthCount.setText(String.valueOf(March.size()));
            }
            if (comMonth.getSelectionModel().isSelected(3)) {
                monthCount.setText(String.valueOf(April.size()));
            }
            if (comMonth.getSelectionModel().isSelected(4)) {
                monthCount.setText(String.valueOf(May.size()));
            }
            if (comMonth.getSelectionModel().isSelected(5)) {
                monthCount.setText(String.valueOf(June.size()));
            }
            if (comMonth.getSelectionModel().isSelected(6)) {
                monthCount.setText(String.valueOf(July.size()));
            }
            if (comMonth.getSelectionModel().isSelected(7)) {
                monthCount.setText(String.valueOf(August.size()));
            }
            if (comMonth.getSelectionModel().isSelected(8)) {
                monthCount.setText(String.valueOf(September.size()));
            }
            if (comMonth.getSelectionModel().isSelected(9)) {
                monthCount.setText(String.valueOf(October.size()));
            }
            if (comMonth.getSelectionModel().isSelected(10)) {
                monthCount.setText(String.valueOf(November.size()));
            }
            if (comMonth.getSelectionModel().isSelected(11)) {
                monthCount.setText(String.valueOf(December.size()));
            }

            monthAmountInterface amount = i -> monthCount.setText(monthCount.getText() + i);
            {
                if (Integer.parseInt(monthCount.getText()) == 1) {
                    amount.countMonth(" appointment this Month.");
                } else {
                    amount.countMonth(" appointments this Month.");
                }
            }

        }
    }

    private void monthBox(){

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

        comMonth.setItems(addMonth);

            }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeBox();
        monthBox();
        appointments = AppointmentQuery.getAllAppointments();

    }

}

