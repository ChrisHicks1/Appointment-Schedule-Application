package controller;

import Database.CustomerQuery;
import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {



    public TableView<Customer> cusTableView;

    public TableColumn<Customer, Integer> cusIdCol;

    public TableColumn<Customer, String> cusNameCol;

    public TableColumn<Customer, String> addressCol;

    public TableColumn<Customer, String> postalCol;

    public TableColumn<Customer, String> phoneCol;

    public TableColumn<Customer, String> countryCol;

    public TableColumn<Customer, String> divisionCol;

    public TableColumn<Customer, Integer> divisionIdCol;


    public void toCusAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void toCusModify(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Modify Customer");
        stage.setScene(scene);
        stage.show();

    }

    public void toCusDelete(ActionEvent actionEvent) {
    }

    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}




    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cusTableView.setItems(CustomerQuery.getCustomer());


        cusIdCol.setCellFactory(new PropertyValueFactory<>("Customer_ID"));
        cusNameCol.setCellFactory(new PropertyValueFactory<>("Customer_Name"));
        addressCol.setCellFactory(new PropertyValueFactory<>("Address"));
        postalCol.setCellFactory(new PropertyValueFactory<>("Postal_Code"));
        phoneCol.setCellFactory(new PropertyValueFactory<>("Phone"));
        countryCol.setCellFactory(new PropertyValueFactory<>("Country"));
        divisionCol.setCellFactory(new PropertyValueFactory<>("Division"));
        divisionIdCol.setCellFactory(new PropertyValueFactory<>("Division_ID"));

        populateCustomerFields();




    }
    void populateCustomerFields(){

    }
}


