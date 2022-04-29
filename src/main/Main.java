package main;


import Database.AppointmentQuery;
import Database.CountryQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Database.DBConnection;
import Database.CustomerQuery;

import java.sql.SQLException;
import java.time.*;
import java.util.Locale;
import java.util.TimeZone;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.show();

    }


    public static void main(String[] args) throws SQLException {
        Locale.setDefault(new Locale("en", "FR/Paris"));


        DBConnection.startConnection();
        //ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("America")).forEach(System.out::println);

        LocalDate parisDate = LocalDate.of(2022, 4, 26);
        LocalTime parisTime = LocalTime.of(5, 0);
        ZoneId parisId = ZoneId.of("Europe/Paris");
        ZonedDateTime parisDT = ZonedDateTime.of(parisDate, parisTime, parisId);
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        Instant parisToGMTInstant = parisDT.toInstant();
        ZonedDateTime parisToLocalDT = parisDT.withZoneSameInstant(localZoneId);
        ZonedDateTime gmtToLocalDT = parisToGMTInstant.atZone(localZoneId);

        System.out.println("Local: " + ZonedDateTime.now());
        System.out.println("Paris: " + parisDT);
        System.out.println("Paris -> GMT: " + parisToGMTInstant);
        System.out.println("GMT -> Local: " + gmtToLocalDT);




        launch(args);
        DBConnection.closeConnection();
    }
}
