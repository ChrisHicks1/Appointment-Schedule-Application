package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label loginLabel;
    @FXML
    private Label ZoneId;
    @FXML
    private Label userName;
    @FXML
    private Label password;
    @FXML
    private Button exitApp;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button loginButton;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        resourceBundle = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr")) {
            loginLabel.setText(resourceBundle.getString("login"));
            ZoneId.setText(resourceBundle.getString("location"));
            userName.setText(resourceBundle.getString("userName"));
            password.setText(resourceBundle.getString("passWord"));
            loginButton.setText(resourceBundle.getString("login"));
            exitApp.setText(resourceBundle.getString("exit"));
        }
    }



    /**button logs into app*/
    public void onLogin(ActionEvent actionEvent) {
    }




    /**button exits app*/
    public void onExitApp(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Application");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Press OK to exit. \nPress Cancel to stay.");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
        else{
            alert.close();
        }
    }
}
