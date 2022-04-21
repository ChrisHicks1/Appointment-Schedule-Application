package controller;

import Database.*;
import com.sun.jdi.Value;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
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
    private ComboBox<Countries> comModifyCountry;
    @FXML
    private ComboBox<Division> comModifyDivision;

    private static Customer selectedCustomer;

    int customerIndex;


    public void init(Customer customer){
        customerIndex = Customer.getAllCustomers().indexOf(customer);
        txtModifyCusId.setText(Integer.toString(customer.getCustomer_ID()));
        txtModifyCusName.setText(customer.getCustomer_Name());
        txtModifyCusAddress.setText(customer.getAddress());
        txtModifyPostal.setText(customer.getPostal_Code());
        txtModifyPhone.setText(customer.getPhone());
       // comModifyCountry.setValue(customer.getCountry());
        comModifyDivision.setPromptText/*setValue*/(customer.getDivision());

    }


//needs to save to sql table as only divisionID while saving to our table as country division and divisionID
    public void onSave(ActionEvent actionEvent) throws IOException, SQLException {
        int Customer_ID = Integer.parseInt(txtModifyCusId.getText());
        String Customer_Name = txtModifyCusName.getText();
        String Address = txtModifyCusAddress.getText();
        String Postal = txtModifyPostal.getText();
        String Phone = txtModifyPhone.getText();
        Countries Country = comModifyCountry.getValue();
        Division Division = comModifyDivision.getValue();
        int Division_ID = comModifyDivision.getValue().getDivisionId();

        CustomerQuery.modifyCustomer(Customer_ID, Customer_Name, Address, Postal, Phone, Division_ID);//Country, Division);


/*
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Modify Success");
                    alert.setContentText("Successfully Modified Customer");
                    Optional<ButtonType> result = alert.showAndWait();

                        if (result.isPresent() && (result.get() == ButtonType.OK)) {
                            Parent modifyCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
                            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            Scene scene = new Scene(modifyCancel, 900, 400);
                            stage.setTitle("Customer View");
                            stage.setScene(scene);
                            stage.show();
                        }
                    }} catch (IOException | SQLException e) {
                        e.printStackTrace();
                        Alert newAlert = new Alert(Alert.AlertType.ERROR);
                        newAlert.setTitle("Error");
                        newAlert.setContentText("Error Loading");
                    }


            }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to Modify Customer");
        }
*/

        {
            Parent modifyCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(modifyCancel, 900, 400);
            stage.setTitle("Customer View");
            stage.setScene(scene);
            stage.show();
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
        if(Country.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Country Required");
            alert.showAndWait();
            return false;
        }
        if(Division.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Division Required");
            alert.showAndWait();
            return false;
        }
        return true;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Countries> allCountries = CountryQuery.getAllCountries();
        ObservableList<Division> allDivisions = DivisionQuery.getAllDivisions();
        ObservableList<Division> states = DivisionQuery.getStates();

        try {
        if (comModifyCountry.getValue().getCountryName().equals("U.S")) {
            comModifyDivision.setItems(states);
        } else {

            comModifyDivision.setItems(allDivisions);
            comModifyDivision.setPromptText("Select Division");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }


        comModifyCountry.setItems(allCountries);


        comModifyDivision.setItems(allDivisions);



    }

    public void onComboCountry(ActionEvent actionEvent) {
    }

    public void onComboDivision(ActionEvent actionEvent) {
    }
}
