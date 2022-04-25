package controller;

import Database.AppointmentQuery;
import Database.CustomerQuery;
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

    public TableView<Customer> cusTableView;

    public TableColumn cusIdCol;

    public TableColumn cusNameCol;

    public TableColumn addressCol;

    public TableColumn postalCol;

    public TableColumn phoneCol;

    public TableColumn countryCol;

    public TableColumn divisionCol;

    public TableColumn divisionIdCol;


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



    //need to finish
    public void toCusDelete(ActionEvent actionEvent) throws SQLException {
        selectedCustomer = cusTableView.getSelectionModel().getSelectedItem();
        selectedCustomerIndex = CustomerQuery.getCustomer().indexOf(selectedCustomer);

        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Customer can NOT be Deleted");
            alert.setContentText("No Customer Selected");
            alert.showAndWait();

        }
        else {
                if (AppointmentQuery.getAssocCustomers(selectedCustomerIndex)){

                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error");
                    alert1.setHeaderText("Associated Appointments");
                    alert1.setContentText("Can NOT Delete Customers With Appointments");
                    alert1.showAndWait();
                } else {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete");
                    alert.setHeaderText("Are You Sure You Want To Delete?");
                    alert.setContentText("Press OK to delete this Customer. \nPress Cancel to cancel.");
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        CustomerQuery.deleteCustomer(selectedCustomer.getCustomer_ID());
                        cusTableView.setItems(CustomerQuery.getCustomer());
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Deleted");
                        alert2.setContentText("Customer has been Deleted");
                        alert2.showAndWait();
                    }
                }
            }
        }
    /* for (int i = 0; i < AppointmentQuery.getAllAppointments().size(); i++) {
                if (Appointments.getAllAppointments().contains(selectedCustomer)) {*/









    public void toMain(ActionEvent actionEvent) throws IOException {goToMain(actionEvent);}




    void goToMain(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(addPartCancel, 250, 450);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            customers = CustomerQuery.getCustomer();


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


