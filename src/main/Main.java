package main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Database.DBConnection;


import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application{

    /**Launches Login Screen, Checks for default language to change Title in login screen*/
    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            primaryStage.setTitle(resourceBundle.getString("login"));
            primaryStage.setScene(new Scene(root, 450, 450));
            primaryStage.show();
        }
    }

/**Starts connection to database when launched and ends connection when program is closed*/
    public static void main(String[] args) throws SQLException {

        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
    }
}
