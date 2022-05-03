package controller;

import Database.CountryDB;
import Database.CustomerDB;
import Database.DivisionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Countries;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @FXML
    private Button addSave;

    @FXML
    private TextField txtAddCusId;
    @FXML
    private TextField txtAddCusName;
    @FXML
    private TextField txtAddCusAddress;
    @FXML
    private TextField txtAddCusPost;
    @FXML
    private TextField txtAddCusPhone;
    @FXML
    private ComboBox<String> comAddCusCountry;
    @FXML
    private ComboBox<String> comAddCusDiv;



    /**Validates information, then adds new Customer and returns to Customer view*/
    public void onSave(ActionEvent actionEvent) throws IOException {
        boolean valid = notEmpty(
                txtAddCusName.getText(),
                txtAddCusAddress.getText(),
                txtAddCusPost.getText(),
                txtAddCusPhone.getText(),
                comAddCusCountry.getValue(),
                comAddCusDiv.getValue());
        if (valid) {
            try {
                CustomerDB.createCustomer(
                        txtAddCusName.getText(),
                        txtAddCusAddress.getText(),
                        txtAddCusPost.getText(),
                        txtAddCusPhone.getText(),
                        comAddCusDiv.getValue());

                Parent cusView = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(cusView, 900, 400);
                stage.setTitle("Customer View");
                stage.setScene(scene);
                stage.show();
            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }



    /**Returns to Customer View on Back button*/
    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}


    /**Helper that returns to Customer View*/
    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addCustomerCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addCustomerCancel, 900, 400);
        stage.setTitle("Customer View");
        stage.setScene(scene);
        stage.show();
    }


    /**Checks that no information is empty*/
    private boolean notEmpty(String Customer_Name, String Address, String Postal_Code, String Phone, String Country, String Division){
        if(Customer_Name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Customer Name Required");
            alert.showAndWait();
            return false;
        }
        if(Address.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Address Required");
            alert.showAndWait();
            return false;
        }
        if(Postal_Code.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Postal_Code Required");
            alert.showAndWait();
            return false;
        }
        if(Phone.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Phone Required");
            alert.showAndWait();
            return false;
        }
        if(comAddCusCountry.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Country Required");
            alert.showAndWait();
            return false;
        }
        if(comAddCusDiv.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Division Required");
            alert.showAndWait();
            return false;
        }
        return true;
    }


    /**Populates Country ComboBox*/
    private void countrySelect(){
        ObservableList<String> addCountries = FXCollections.observableArrayList();

        try {
            ObservableList<Countries> allCountries = CountryDB.getAllCountries();
            for(Countries countries : allCountries){
                addCountries.add(countries.getCountryName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comAddCusCountry.setItems(addCountries);

    }


    /**Populates Division ComboBox*/
    private void divisionSelect(){
        ObservableList<String> addDivision = FXCollections.observableArrayList();

        try {
            ObservableList<Division> allDivisions = DivisionDB.getAllDivisions();
            for(Division division : allDivisions){
                addDivision.add(division.getDivision());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comAddCusDiv.setItems(addDivision);

    }

    /**After selecting country, modifies division list to match country selection*/
    public void onComboCountry(ActionEvent actionEvent){
        ObservableList<String> dlist = FXCollections.observableArrayList();
        try{
            ObservableList<Division> divisions = DivisionDB.getStates(comAddCusCountry.getSelectionModel().getSelectedItem());

            assert divisions != null;
            for (Division division : divisions) {
                dlist.add(division.getDivision());
            }
            comAddCusDiv.setItems(dlist);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    /**Initializing division and country ComboBoxes*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        divisionSelect();
        countrySelect();
    }



    public void onComboDivision(ActionEvent actionEvent) {
    }
}
