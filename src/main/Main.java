package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.show();

    }


    public static void main(String[] args) {
        Locale.setDefault(new Locale("fr"));
        launch(args);

        ResourceBundle rb = ResourceBundle.getBundle("/main/Nat", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr")){
            System.out.println(rb.getString("hello") + " " + rb.getString("world"));
        }

        JDBC.openConnection();
        JDBC.closeConnection();


    }
}
