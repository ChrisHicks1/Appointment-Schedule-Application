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

    }

    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}




    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 850, 700);
        stage.setTitle("Back to Main Screen");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
