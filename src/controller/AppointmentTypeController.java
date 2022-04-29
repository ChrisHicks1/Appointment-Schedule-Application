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
import java.sql.SQLException;
import java.util.ArrayList;
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

        ObservableList<Appointments> noTypes = AppointmentQuery.getAllAppointments();
        assert noTypes != null;
        for(Appointments appointments : noTypes){
            addType.add(appointments.getType());

    }
        comType.setItems(addType);
    }

    @FXML
    private void onComType(ActionEvent actionEvent){
        ObservableList<String> tlist = FXCollections.observableArrayList();
        try{
            ObservableList<Appointments> appointments = AppointmentQuery.getAllTypes(comType.getSelectionModel().getSelectedItem());


            for (Appointments appointments1 : appointments) {

                tlist.add(appointments1.getType());
            }
            comType.setItems(tlist);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onComMonth(){
       /* ObservableList<String> mlist = FXCollections.observableArrayList();
        try{
            ObservableList<Appointments> appointments = AppointmentQuery.getAllAppointments();

            for(Appointments appointments1 : appointments){
                appointments1.getStart().getMonth(January);
            }
        }*/
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

        monthType.setText(appointments.size() + " Appointments Scheduled");

    }




    private void amountMonth() {


            }






}

