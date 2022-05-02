package controller;

import Database.CustomerDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController {

    @FXML
    private TableView appTableView;
    @FXML
    private TableColumn appIdCol;
    @FXML
    private TableColumn appCusIdCol;
    @FXML
    private TableColumn contactIdCol;
    @FXML
    private TableColumn locationCol;
    @FXML
    private TableColumn titleCol;
    @FXML
    private TableColumn startCol;
    @FXML
    private TableColumn endCol;

    @FXML
    private TableView <CustomerDB> cusTableView;
    @FXML
    private TableColumn  cusIdCol;
    @FXML
    private TableColumn  cusNameCol;
    @FXML
    private TableColumn  cusPhoneCol;












        public void toViewCalendar(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentCalendar.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 450);
                stage.setTitle("Appointment Calendar");
                stage.setScene(scene);
                stage.show();
            }


        public void toViewCustomers(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 400);
            stage.setTitle("Customer View");
            stage.setScene(scene);
            stage.show();
        }


        public void toAppTypes(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentType.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 425, 275);
            stage.setTitle("Appointment Types");
            stage.setScene(scene);
            stage.show();
        }

        public void toContactSched(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ContactSchedule.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 775, 450);
            stage.setTitle("Contact Schedule");
            stage.setScene(scene);
            stage.show();
        }

        public void toLogOut(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 450, 450);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }

    public void toUser(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/UserSchedule.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 450);
            stage.setTitle("User Schedule");
            stage.setScene(scene);
            stage.show();
        }



    public class MainMenu implements Initializable {

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {



                cusIdCol.setCellFactory(new PropertyValueFactory<>("customerId"));
                cusNameCol.setCellFactory(new PropertyValueFactory<>("customerName"));
                cusPhoneCol.setCellFactory(new PropertyValueFactory<>("phone"));

        }

    }
}