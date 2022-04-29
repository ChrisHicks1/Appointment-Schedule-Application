package controller;

import Database.AppointmentQuery;
import Database.DivisionQuery;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Division;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class AppointmentTypeController implements Initializable {

    static ObservableList<Appointments> appointments = FXCollections.observableArrayList();


    @FXML
    private ComboBox comMonth;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        typeBox();

       // appointments = AppointmentQuery.getAllAppointments();

      //  monthType.setText(appointments.size() + " Appointments Scheduled");

    }




    private void amountMonth() {


            }






}

