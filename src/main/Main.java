package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.show();

    }


    public static void main(String[] args) {
        Locale.setDefault(new Locale("fr"));


        JDBC.openConnection();
        JDBC.closeConnection();
        launch(args);
    }
}
