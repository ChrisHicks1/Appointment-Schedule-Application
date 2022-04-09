package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


interface LoginActivity{
    public String getFileName();
}


public class LoginController implements Initializable {

    LoginActivity loginActivity = () -> {
        return "login_activity.txt";
    };

    private ResourceBundle resourceBundle;
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
    public void onLogin(ActionEvent actionEvent) throws IOException {
        userNameEmpty(userName.getText());
        passwordEmpty(password.getText());

        fileCreate();

        try{
            boolean valid = userSearch.checkUserAndPassword(userName.getText(), password.getText());
        if (valid) {
            loginSucess();

            try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 850, 700);
            stage.setTitle("Add Product");
            stage.setScene(scene);
            stage.show();
        }
            catch (Exception e){
                e.printStackTrace();

                if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"));
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("error"));
                alert.setContentText(resourceBundle.getString("loadError"));
                alert.showAndWait();
            }
        }
        else {
            loginFail();

            if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("error"));
            alert.setContentText(resourceBundle.getString("invalidLogin"));
            alert.showAndWait();
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    // alert to appointment on loginSuccess


    private void alertAppointment(){

    }




    /**Writes Successful login attmept*/
    private void loginSucess(){

    alertAppointment();

        try {
            FileWriter fileWriter = new FileWriter(loginActivity.getFileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("Successful Login: Username: " + userName.getText() + " Password: " + password.getText() + " TimeStamp: " + simpleDateFormat);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Writes failed login attempt*/
    private void loginFail(){
        try{
            FileWriter fileWriter = new FileWriter(loginActivity.getFileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("Failed Login: Username: " + userName.getText() + " Password: " + password.getText() + " TimeStamp: " + simpleDateFormat);
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }



    /**Checks if username field is filled in*/
    private void userNameEmpty(String userName){
        if(userName.isEmpty()){
            if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("error"));
            alert.setContentText(resourceBundle.getString("userNameRequired"));
            alert.showAndWait();
        }
    }

    /**Checks if password field is filled in*/
    private void passwordEmpty(String password){
        if(password.isEmpty()){
            if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("error"));
            alert.setContentText(resourceBundle.getString("passwordRequired"));
            alert.showAndWait();
        }
    }


    private void fileCreate(){
        try {
            File file = new File(loginActivity.getFileName());
            if(file.createNewFile()){
                System.out.println("New File Created: " + file.getName());
            }
            else{
                System.out.println("File Already Exists. Loacted: " + file.getPath());
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
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
