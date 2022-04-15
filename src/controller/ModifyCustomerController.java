package controller;

import Database.CustomerQuery;
import Database.DBConnection;
import Database.DBQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyCustomerController {
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
        txtModifyCusName.setText(customer.getCustomer_Name());
        txtModifyCusAddress.setText(customer.getAddress());
        txtModifyPostal.setText(customer.getPostal_Code());
        txtModifyPhone.setText(customer.getPostal_Code());

    }




    public void onSave(ActionEvent actionEvent) throws IOException{
       /* boolean valid = notEmpty(txtModifyCusName.getText(), txtModifyCusAddress.getText(), txtModifyPostal.getText(), txtModifyPhone.getText());

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
}
