package controller;

import Database.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
    private ComboBox<Countries> comModifyCountry;
    @FXML
    private ComboBox<Division> comModifyDivision;

    int customerIndex;

    public void init(Customer customer){
        customerIndex = Customer.getAllCustomers().indexOf(customer);
        txtModifyCusId.setText(Integer.toString(customer.getCustomer_ID()));
        txtModifyCusName.setText(customer.getCustomer_Name());
        txtModifyCusAddress.setText(customer.getAddress());
        txtModifyPostal.setText(customer.getPostal_Code());
        txtModifyPhone.setText(customer.getPostal_Code());
        comModifyCountry.setPromptText(customer.getCountry());
        comModifyDivision.setPromptText(customer.getDivision());

    }




    public void onSave(ActionEvent actionEvent) throws IOException{
     /*   boolean valid = notEmpty(txtModifyCusName.getText(), txtModifyCusAddress.getText(), txtModifyPostal.getText(), txtModifyPhone.getText());

        if(valid){
            try {
                boolean success = CustomerQuery.updateCustomer(Integer.parseInt(txtModifyCusId.getText()), txtModifyCusName.getText(), txtModifyPostal.getText(), txtModifyPhone.getText(), comModifyCountry.getValue(), comModifyDivision.getValue());
            }
        }


        {
            Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(addPartCancel, 900, 400);
            stage.setTitle("Customer View");
            stage.setScene(scene);
            stage.show();
        }*/
    }

  //  private boolean isEmpty(String User_Name, String Address, String Postal_Code, String Phone, String Country, String Division){

 //   }






    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}


    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 900, 400);
        stage.setTitle("Customer View");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Countries> allCountries = CountryQuery.getAllCountries();
        ObservableList<Division> allDivisions = DivisionQuery.getAllDivisions();


        comModifyCountry.setItems(allCountries);


        comModifyDivision.setItems(allDivisions);



    }

    public void onComboCountry(ActionEvent actionEvent) {
    }

    public void onComboDivision(ActionEvent actionEvent) {
    }
}
