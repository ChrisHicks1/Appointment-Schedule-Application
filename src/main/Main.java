package main;


import Database.DBCountries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Database.DBConnection;

import java.util.Locale;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.show();

    }


    public static void main(String[] args) {
        Locale.setDefault(new Locale("fr"));


        DBConnection.startConnection();
        DBCountries.checkDateConversion();

        launch(args);
        DBConnection.closeConnection();
    }
}
