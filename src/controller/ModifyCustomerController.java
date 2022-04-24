package controller;

import Database.*;
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
import model.Countries;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyCustomerController implements Initializable {
    @FXML
    private Button modifySave;
    @FXML
    private TextField txtModifyCusId;
    @FXML
    private TextField txtModifyCusName;
    @FXML
    private TextField txtModifyCusAddress;
    @FXML
    private TextField txtModifyPostal;
    @FXML
    private TextField txtModifyPhone;
    @FXML
    private ComboBox<String> comModifyCountry;
    @FXML
    private ComboBox<String> comModifyDivision;


    int customerIndex;


    public void init(Customer customer){
        customerIndex = Customer.getAllCustomers().indexOf(customer);
        txtModifyCusId.setText(Integer.toString(customer.getCustomer_ID()));
        txtModifyCusName.setText(customer.getCustomer_Name());
        txtModifyCusAddress.setText(customer.getAddress());
        txtModifyPostal.setText(customer.getPostal_Code());
        txtModifyPhone.setText(customer.getPhone());
        comModifyCountry.setValue(customer.getCountry());
        comModifyDivision.setValue(customer.getDivision());

    }


    public void onSave(ActionEvent actionEvent) throws IOException {

        boolean valid = notEmpty(
                txtModifyCusName.getText(),
                txtModifyCusAddress.getText(),
                txtModifyPostal.getText(),
                txtModifyPhone.getText(),
                comModifyCountry.getValue(),
                comModifyDivision.getValue());
        if (valid) {
            try {
                CustomerQuery.modifyCustomer(
                        Integer.parseInt(txtModifyCusId.getText()),
                        txtModifyCusName.getText(),
                        txtModifyCusAddress.getText(),
                        txtModifyPostal.getText(),
                        txtModifyPhone.getText(),
                        comModifyDivision.getValue());

                Parent modifyCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(modifyCancel, 900, 400);
                stage.setTitle("Customer View");
                stage.setScene(scene);
                stage.show();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }





public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 900, 400);
        stage.setTitle("Customer View");
        stage.setScene(scene);
        stage.show();
    }

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
        if(comModifyCountry.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Country Required");
            alert.showAndWait();
            return false;
        }
        if(comModifyDivision.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Division Required");
            alert.showAndWait();
            return false;
        }
        return true;
    }


    private void countryBox(){
        ObservableList<String> modifyCountries = FXCollections.observableArrayList();

        try {
            ObservableList<Countries> allCountries = CountryQuery.getAllCountries();
            for(Countries countries: allCountries){
                if(!modifyCountries.contains(countries.getCountryName())){
                    modifyCountries.add(countries.getCountryName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comModifyCountry.setItems(modifyCountries);
    }


    private void divisionBox(){
        ObservableList<String> modifyDivision = FXCollections.observableArrayList();

        try {
            ObservableList<Division> allDivisions = DivisionQuery.getAllDivisions();
            for(Division division: allDivisions){
                if(!modifyDivision.contains(division.getDivision())){
                    modifyDivision.add(division.getDivision());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        comModifyDivision.setItems(modifyDivision);
    }

    public void onComboCountry(ActionEvent actionEvent){
        ObservableList<String> dlist = FXCollections.observableArrayList();
        try{
            ObservableList<Division> divisions = DivisionQuery.getStates(comModifyCountry.getSelectionModel().getSelectedItem());

            assert divisions != null;
            for (Division division : divisions) {
                dlist.add(division.getDivision());
            }
            comModifyDivision.setItems(dlist);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        divisionBox();
        countryBox();
    }

    public void onComboDivision(ActionEvent actionEvent) {
    }
}
