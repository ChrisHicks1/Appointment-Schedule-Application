package controller;

import Database.AppointmentDB;
import Database.CustomerDB;
import javafx.fxml.FXML;
import model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {


    static ObservableList<Customer> customers;

    @FXML
    private TableView<Customer> cusTableView;
    @FXML
    private TableColumn cusIdCol;
    @FXML
    private TableColumn cusNameCol;
    @FXML
    private TableColumn addressCol;
    @FXML
    private TableColumn postalCol;
    @FXML
    private TableColumn phoneCol;
    @FXML
    private TableColumn countryCol;
    @FXML
    private TableColumn divisionCol;
    @FXML
    private TableColumn divisionIdCol;


    /**Takes user to Add Customer Menu when Add button is clicked*/
    public void toCusAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 450, 550);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    private static Customer selectedCustomer;
    private static int selectedCustomerIndex;


    /**Verifies that a customer has been selected and then takes user to Modify Customer Menu when Modify button is clicked*/
    public void toCusModify(ActionEvent actionEvent) throws IOException {

        selectedCustomer = cusTableView.getSelectionModel().getSelectedItem();
        selectedCustomerIndex = Customer.getAllCustomers().indexOf(selectedCustomer);
        if (selectedCustomer == null) {
            Alert nullAlert = new Alert(Alert.AlertType.ERROR);
            nullAlert.setTitle("Error");
            nullAlert.setHeaderText("Customer can NOT be Modified");
            nullAlert.setContentText("No Customer Selected");
            nullAlert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifyCustomer.fxml"));
                Parent modifyCustomer = loader.load();
                ModifyCustomerController modifiedCustomer = loader.getController();
                modifiedCustomer.init(selectedCustomer);

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(modifyCustomer, 450, 550);
                stage.setTitle("Modify Customer");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



    /**Verifies a customer has been selected, then verifies that no Appointments are associated with Customer
     * before promoting to delete customer when Delete button is clicked*/
    public void toCusDelete(ActionEvent actionEvent) throws SQLException {
        selectedCustomer = cusTableView.getSelectionModel().getSelectedItem();
        selectedCustomerIndex = CustomerDB.getCustomer().indexOf(selectedCustomer);

        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Customer can NOT be Deleted");
            alert.setContentText("No Customer Selected");
            alert.showAndWait();
        }
        else {
            boolean valid = checkApps(selectedCustomer);
                if (valid){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete");
                    alert.setHeaderText("Are You Sure You Want To Delete?");
                    alert.setContentText("Press OK to delete this Customer. \nPress Cancel to cancel.");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        CustomerDB.deleteCustomer(selectedCustomer.getCustomer_ID());
                        cusTableView.setItems(CustomerDB.getCustomer());
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Deleted");
                        alert2.setContentText("Customer has been Deleted");
                        alert2.showAndWait();
                    }
                }
                else{
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error");
                    alert1.setHeaderText("Associated Appointments");
                    alert1.setContentText("Can NOT Delete Customers With Appointments");
                    alert1.showAndWait();
                }
            }
        }


        /**Helper to check if Customer has any Appointments*/
        private boolean checkApps(Customer selectedCustomer){
        try{
            ObservableList<Appointments> appointments = AppointmentDB.getAssocCustomers(selectedCustomer.getCustomer_ID());
            return appointments != null && appointments.size() < 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        }






    /**Takes user back to Main Menu when Back button is clicked*/
    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}

    /**Helper to take user back to Main Menu*/
    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent toMainMenu = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(toMainMenu, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }




    /**initializes Customer table*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            customers = CustomerDB.getCustomer();

            cusIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            cusNameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            postalCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
            divisionCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
            divisionIdCol.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
            cusTableView.setItems(customers);

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}


