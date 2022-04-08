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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.show();

    }


    public static void main(String[] args) {
        Locale.setDefault(new Locale("en"));

        Locale france = new Locale("en", "EN");


        ResourceBundle rb = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr")){
            System.out.println(rb.getString("hello") + " " + rb.getString("world") + " " + rb.getString("login"));
        }

        JDBC.openConnection();
        JDBC.closeConnection();
        launch(args);
    }
}
