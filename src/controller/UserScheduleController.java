package controller;

import Database.AppointmentDB;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserScheduleController implements Initializable {
    @FXML
    private ComboBox<Integer> comUser;
    @FXML
    private TableView userTableView;
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
    private TableColumn contCol;

    private static ObservableList<Appointments> appointments = FXCollections.observableArrayList();

    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}

    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainMenu, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onCombo(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointments> appointments = AppointmentDB.getAssocUsers(comUser.getSelectionModel().getSelectedItem());
        userTableView.setItems(appointments);
        userTableView.refresh();

    }


    private void userIDBox(){
        ObservableList<Integer> addUsers = FXCollections.observableArrayList();

        try {
            ObservableList<Users> allUsers = UserDB.getAllUsers();
            for(Users users: allUsers){
                if(!addUsers.contains(users.getUser_ID())){
                    addUsers.add(users.getUser_ID());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comUser.setItems(addUsers);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userIDBox();


        appointments = AppointmentDB.getAllAppointments();


        appCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        contCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
        userTableView.setItems(appointments);

    }

}
