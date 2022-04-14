package main;


import Database.CountryQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Database.DBConnection;
import Database.CustomerQuery;

import java.sql.SQLException;
import java.util.Locale;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.show();

    }


    public static void main(String[] args) throws SQLException {
        Locale.setDefault(new Locale("en"));


        DBConnection.startConnection();
        CountryQuery.checkDateConversion();


        launch(args);
        DBConnection.closeConnection();
    }
}
