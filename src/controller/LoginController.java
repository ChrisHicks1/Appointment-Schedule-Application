package controller;


import Database.AppointmentQuery;
import Database.UserQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.*;


interface LoginActivity{
    public String getFileName();
}


public class LoginController implements Initializable {



    LoginActivity loginActivity = () -> {
        return "login_activity.txt";
    };

    private ResourceBundle resourceBundle;
    @FXML
    private Label timeZone;
    @FXML
    private TextField timeField;
    @FXML
    private TextField country;
    @FXML
    private Label loginLabel;
    @FXML
    private Label location;
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

        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            loginLabel.setText(resourceBundle.getString("login"));
            location.setText(resourceBundle.getString("location"));
            country.setText(resourceBundle.getString("country"));
            //timeField.setText(String.valueOf(TimeZone.getDefault().getDisplayName()));
            timeZone.setText(String.valueOf(TimeZone.getDefault().getID()));
            userName.setText(resourceBundle.getString("userName"));
            password.setText(resourceBundle.getString("passWord"));
            loginButton.setText(resourceBundle.getString("login"));
            exitApp.setText(resourceBundle.getString("exit"));
        }
    }



    /**button logs into app*/
    public void onLogin(ActionEvent actionEvent) throws IOException{


        userNameEmpty(txtUserName.getText());
        passwordEmpty(txtPassword.getText());

        fileCreate();

        try{
            boolean valid = UserQuery.checkUserAndPassword(txtUserName.getText(), txtPassword.getText());
        if (valid) {
            loginSuccess();

            try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 250, 450);
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();
        }
            catch (IOException e){
                e.printStackTrace();

                if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(resourceBundle.getString("error"));
                    alert.setContentText(resourceBundle.getString("loadError"));
                    alert.showAndWait();
                }
            }
        }
        else {
            loginFail();

            if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("error"));
                alert.setContentText(resourceBundle.getString("invalidLogin"));
                alert.showAndWait();
            }
        }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }



    // alert to appointment on loginSuccess
    private void alertAppointment() throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime15 = localDateTime.plusMinutes(15);
       /* if(AppointmentQuery.getMinutes()){
            Alert appAlert = new Alert(Alert.AlertType.INFORMATION);
            appAlert.setTitle("Appointment");
            appAlert.setContentText("You have an Appointment in 15 minutes or less");
            appAlert.showAndWait();
        }
        else{
            Alert appAlert1 = new Alert(Alert.AlertType.INFORMATION);
            appAlert1.setTitle("Appointment");
            appAlert1.setContentText("You do NOT have an upcoming Appointment");
            appAlert1.showAndWait();
        }*/
    }




    /**Writes Successful login attempt*/
    private void loginSuccess() throws SQLException {

    alertAppointment();

        try {
            FileWriter fileWriter = new FileWriter(loginActivity.getFileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("\nSuccessful Login: Username: " + txtUserName.getText() + " Password: " + txtPassword.getText() + " TimeStamp: " + date + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Writes failed login attempt*/
    private void loginFail(){
        try{
            FileWriter fileWriter = new FileWriter(loginActivity.getFileName(), true);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("\nFailed Login: Username: " + txtUserName.getText() + " Password: " + txtPassword.getText() + " TimeStamp: " + date + "\n");
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }



    /**Checks if username field is filled in*/
    private void userNameEmpty(String txtUserName){
        if(txtUserName.isEmpty()) {
            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("error"));
                alert.setContentText(resourceBundle.getString("userNameRequired"));
                alert.showAndWait();
            }
        }
    }

    /**Checks if password field is filled in*/
    private void passwordEmpty(String txtPassword){
        if(txtPassword.isEmpty()) {
            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("error"));
                alert.setContentText(resourceBundle.getString("passwordRequired"));
                alert.showAndWait();
            }
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
