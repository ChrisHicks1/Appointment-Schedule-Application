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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class AppointmentTypeController implements Initializable {

    static ObservableList<Appointments> appointments = FXCollections.observableArrayList();





    @FXML
    private ListView<Appointments> listType;
    @FXML
    private Label monthApp;
    @FXML
    private TableColumn typeAmount;
    @FXML
    private TableView appTypeTable;
    @FXML
    private TableColumn appType;
    @FXML
    private ToggleButton january;
    @FXML
    private ToggleButton february;
    @FXML
    private ToggleButton march;
    @FXML
    private ToggleButton april;
    @FXML
    private ToggleButton may;
    @FXML
    private ToggleButton june;
    @FXML
    private ToggleButton july;
    @FXML
    private ToggleButton august;
    @FXML
    private ToggleButton september;
    @FXML
    private ToggleButton october;
    @FXML
    private ToggleButton november;
    @FXML
    private ToggleButton december;
    @FXML
    private ToggleGroup tGroup;



    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}

    public interface typeAmountInterface{
        void amountCount(String i);
    }


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    //public void typeCount = Appointments.getAllAppointments();



    typeAmountInterface amount = i -> System.out.println("Some" + i); {

        amount.amountCount("thing");
    };

    public void init(Appointments appointments){
    listType.setId(appointments.getType());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointments = AppointmentQuery.getAllAppointments();

        appTypeTable.setItems(appointments);

        appType.setCellValueFactory(new PropertyValueFactory<>("Type"));


    }
    ObservableList<Integer> jan = FXCollections.observableArrayList();

    private void amountMonth() {
        try {
            ObservableList<Appointments> typesMonth = AppointmentQuery.getMonth();

            ObservableList<Appointments> appointments1 = AppointmentQuery.getAllAppointments();
            if (appointments1 != null) {
                for (Appointments appointments : appointments1) {
                    String Type = appointments.getType();
                    LocalDate Start = appointments.getStartDate();

                    if (Start.getMonth().equals(Month.of(1))) {
                        jan.add(Start.getMonthValue());
                    }


                    monthApp.setText(Integer.toString(typesMonth.size()) + " Appointments this Month");

                    //typeAmount.setCellValueFactory(new PropertyValueFactory<>(typeAmount.getId()));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }





    public void on1(ActionEvent actionEvent) {

        appTypeTable.setItems(jan);
        appTypeTable.refresh();
    }

        public void on2(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on3(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on4(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on5(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on6(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on7(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on8(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on9(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on10(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on11(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }

    public void on12(ActionEvent actionEvent) {

        appTypeTable.refresh();
    }
}

