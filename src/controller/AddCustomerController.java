package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Customer;

import java.io.IOException;
import java.net.URL;
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
    private ComboBox comAddCusCountry;
    @FXML
    private ComboBox comAddCusDiv;






    public void onSave(ActionEvent actionEvent) throws IOException{
        String Customer_ID = txtAddCusId.getText();
        String Customer_Name = txtAddCusName.getText();
        String Address = txtAddCusAddress.getText();
        String Postal_Code = txtAddCusPost.getText();
        String Phone = txtAddCusPhone.getText();

        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 900, 400);
        stage.setTitle("Customer View");
        stage.setScene(scene);
        stage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
