package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
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
    private TableColumn cusIdCol;
    @FXML
    private TableColumn cusNameCol;
    @FXML
    private TableColumn cusPhoneCol;

    public void toCusAdd(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();

    }

    public void toCusModify(ActionEvent actionEvent) {
    }

    public void toCusDelete(ActionEvent actionEvent) {
    }





    public void toAppAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    public void toAppModify(ActionEvent actionEvent) {
    }

    public void toAppDelete(ActionEvent actionEvent) {
    }










    public void toViewCalendar(ActionEvent actionEvent) {
    }

    public void toAppTypes(ActionEvent actionEvent) {
    }

    public void toContactSched(ActionEvent actionEvent) {
    }

    public void toLogOut(ActionEvent actionEvent) {
    }
}
