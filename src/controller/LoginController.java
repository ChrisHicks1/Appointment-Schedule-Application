package controller;


import Database.AppointmentDB;
import Database.UserDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;



public class LoginController implements Initializable {




    @FXML
    private Label timeZone;
    @FXML
    private TextField timeField;
    @FXML
    private Label loginLabel;
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


   public static void zoneIds(){
       ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
   }



    Locale locEst= new Locale("en", "US/East");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        resourceBundle = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            loginLabel.setText(resourceBundle.getString("login"));
            timeField.setText(String.valueOf(ZoneId.systemDefault()));//TimeZone.getDefault().getDisplayName()))); TimeZone.getDefault().getDisplayName());
            timeZone.setText(resourceBundle.getString("timeZone"));
            userName.setText(resourceBundle.getString("userName"));
            password.setText(resourceBundle.getString("passWord"));
            loginButton.setText(resourceBundle.getString("login"));
            exitApp.setText(resourceBundle.getString("exit"));
        }
    }


    ResourceBundle resourceBundle = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());

    /**button logs into app*/
    public void onLogin(ActionEvent actionEvent) throws SQLException {

        fileCreate();
        boolean valid = loginEmpty(txtUserName.getText(), txtPassword.getText());
        boolean valid1 = UserDB.checkUserAndPassword(txtUserName.getText(), txtPassword.getText());

        try{

        if (valid && valid1) {
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
            }
        }
        else if(valid){
            wrongUserOrPass();
            loginFail();
        }
        else if(!valid1) {
            loginFail();
        }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }



    /**Alerts if there is an Appointment within 15 minutes, if not then alerts no Appointment in 15 minutes*/
    private void alertAppointment() throws SQLException {
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTime15 = localDateTimeNow.plusMinutes(15);
        ObservableList<Appointments> appointment15 = AppointmentDB.getAllAppointments();

        for (Appointments appointments : appointment15) {
            boolean within15 = localDateTimeNow.isBefore(appointments.getStart()) && localDateTime15.isAfter(appointments.getStart()) || (localDateTime15.isEqual(appointments.getStart()));
            if (within15) {
                if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                    Alert appAlert = new Alert(Alert.AlertType.INFORMATION);
                    appAlert.setTitle(resourceBundle.getString("appointment"));
                    appAlert.setContentText(resourceBundle.getString("appointment15") + " " + "\nAppointment ID: " + appointments.getAppointment_ID() + " " + "\nAppointment is at: " + appointments.getStart());
                    appAlert.showAndWait();
                    return;
                }
            }
        }
            for (Appointments appointments : appointment15) {
            boolean not15 = (appointments.getStart().isAfter(localDateTime15));
            if(not15) {
                if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                        Alert appAlert1 = new Alert(Alert.AlertType.INFORMATION);
                        appAlert1.setTitle(resourceBundle.getString("appointment"));
                        appAlert1.setContentText(resourceBundle.getString("noAppointment"));
                        appAlert1.showAndWait();
                        return;
                    }
                }
            }
        }




    /**Writes Successful login attempt*/
    private void loginSuccess() throws SQLException {

        try {
            alertAppointment();

            FileWriter fileWriter = new FileWriter("login_activity.txt", true);
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
            FileWriter fileWriter = new FileWriter("login_activity.txt", true);
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write("\nFailed Login: Username: " + txtUserName.getText() + " Password: " + txtPassword.getText() + " TimeStamp: " + date + "\n");
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    /**Alerts if UserName or Password is incorrect*/
    private void wrongUserOrPass(){
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("error"));
            alert.setContentText(resourceBundle.getString("incorrectNamePass"));
            alert.showAndWait();
        }
    }



    /**Checks if UserName and Password fields are filled in*/
    private boolean loginEmpty(String txtUserName, String txtPassword) {
        if (txtUserName.isEmpty()) {
            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("error"));
                alert.setContentText(resourceBundle.getString("userNameRequired"));
                alert.showAndWait();
                return false;
            }
        }
        else if (txtPassword.isEmpty()) {
            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resourceBundle.getString("error"));
                alert.setContentText(resourceBundle.getString("passwordRequired"));
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }




    /**Creates Login File*/
     private void fileCreate() {
         File file = new File("login_activity.txt");
     }




    /**button exits app*/
    public void onExitApp(ActionEvent actionEvent){
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString("exitApp"));
        alert.setHeaderText(resourceBundle.getString("areyousureexit"));
        alert.setContentText(resourceBundle.getString("pressOK"));
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
}
