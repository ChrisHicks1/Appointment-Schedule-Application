package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label loginLabel;
    public Label ZoneId;
    public Label userName;
    public Label password;
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
        }
    }

    public void onLogin(ActionEvent actionEvent) {
    }
}
