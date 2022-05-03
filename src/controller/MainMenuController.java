package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


/**Controller for Main Menu Screen*/
public class MainMenuController {


        /**Takes user to Appointment Calendar on View Calendar button*/
        public void toViewCalendar(ActionEvent actionEvent) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentCalendar.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 450);
                stage.setTitle("Appointment Calendar");
                stage.setScene(scene);
                stage.show();
            }


        /**Takes user to Customer View on View Customers button*/
        public void toViewCustomers(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 400);
            stage.setTitle("Customer View");
            stage.setScene(scene);
            stage.show();
        }


        /**Takes user to Appointment Types Calendar on Appointment Type button*/
        public void toAppTypes(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentType.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 425, 275);
            stage.setTitle("Appointment Types");
            stage.setScene(scene);
            stage.show();
        }


        /**Takes user to Contact Schedule Calendar on Contact Schedule button*/
        public void toContactSched(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ContactSchedule.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 775, 450);
            stage.setTitle("Contact Schedule");
            stage.setScene(scene);
            stage.show();
        }


        /**Takes user to User Schedule Calendar on User Schedule button*/
       public void toUser(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/UserSchedule.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 450);
            stage.setTitle("User Schedule");
            stage.setScene(scene);
            stage.show();
        }

        /**Returns User to Login Screen on Log Out button*/
    public void toLogOut(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 450);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

}