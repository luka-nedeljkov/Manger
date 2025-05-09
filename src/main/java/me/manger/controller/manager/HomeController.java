package me.manger.controller.manager;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import me.manger.controller.manager.dialog.home.EditPropertyController;
import me.manger.model.building.Building;
import me.manger.model.Database;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;
import me.manger.model.user.paymentHistory.HistoryEntry;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label lblBalance;
    @FXML
    private Label lblBuilding;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPhone;

    @FXML
    private Label lblOwner1;
    @FXML
    private Label lblOwner2;
    @FXML
    private Label lblOwner3;
    @FXML
    private ListView<Property> lstProperties;
    @FXML
    private TableView<HistoryEntry> tblHistory;
    @FXML
    private TableColumn<HistoryEntry, String> clmTimestamp;
    @FXML
    private TableColumn<HistoryEntry, String> clmValue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Building building = Database.session.activeBuilding;
        Manager manager = (Manager) Database.session.loggedIn;

        lblBuilding.setText(building.address);
        lblBalance.setText(building.balance + " Din");

        lblName.setText(manager.name);
        lblEmail.setText(manager.email);
        lblPhone.setText(manager.phone);

        clmTimestamp.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmValue.setCellValueFactory(new PropertyValueFactory<>("amount"));

        lstProperties.getItems().addAll(building.properties);
        lstProperties.getItems().addAll(building.garages);
        lstProperties.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> populateOwnerNames(t1));
        if(!lstProperties.getItems().isEmpty()) {
            lstProperties.getSelectionModel().select(0);
        }
    }

    @FXML
    void depositTable(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/me/manger/view/manager/dialog/home/TableDeposits.fxml"))));
        stage.setTitle("Tabela uplata");
        stage.show();
    }

    @FXML
    void withdrawalTable(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/me/manger/view/manager/dialog/home/TableWithdrawals.fxml"))));
        stage.setTitle("Tabela i splata");
        stage.show();
    }

    @FXML
    void deposit(ActionEvent event) throws IOException {
        if(Database.session.activeBuilding.properties.isEmpty() && Database.session.activeBuilding.garages.isEmpty()) {
            return;
        }

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/me/manger/view/manager/dialog/home/Deposit.fxml"))));
        stage.setTitle("Evidentiraj uplatu");
        stage.showAndWait();
        populateOwnerNames(lstProperties.getSelectionModel().getSelectedItem());
        lblBalance.setText(Database.session.activeBuilding.balance + " Din");
    }

    @FXML
    void withdraw(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/me/manger/view/manager/dialog/home/Withdrawal.fxml"))));
        stage.setTitle("Evidentiraj isplatu");
        stage.showAndWait();
        lblBalance.setText(Database.session.activeBuilding.balance + " Din");
    }

    @FXML
    void meeting(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/me/manger/view/manager/dialog/home/Meeting.fxml"))));
        stage.setTitle("Zakaži sastanak");
        stage.show();
    }

    @FXML
    void editProperty(ActionEvent event) throws IOException {
        if(lstProperties.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/manger/view/manager/dialog/home/EditProperty.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((EditPropertyController) loader.getController()).init(lstProperties.getSelectionModel().getSelectedItem());

        stage.setResizable(false);
        stage.setTitle("Izmeni podatke");
        stage.showAndWait();
        populateOwnerNames(lstProperties.getSelectionModel().getSelectedItem());
    }

    public void populateOwnerNames(Property t1) {
        String[] owners = t1.ownerNames.split(", ");
        switch(owners.length) {
            case 0 -> {
                lblOwner1.setText("");
                lblOwner2.setText("");
                lblOwner3.setText("");
            }
            case 1 -> {
                lblOwner1.setText(owners[0]);
                lblOwner2.setText("");
                lblOwner3.setText("");
            }
            case 2 -> {
                lblOwner1.setText(owners[0]);
                lblOwner2.setText(owners[1]);
                lblOwner3.setText("");
            }
            case 3 -> {
                lblOwner1.setText(owners[0]);
                lblOwner2.setText(owners[1]);
                lblOwner3.setText(owners[2]);
            }
            default -> {
                lblOwner1.setText(owners[0]);
                lblOwner2.setText(owners[1]);
                lblOwner3.setText("...");
            }
        }
        FilteredList<HistoryEntry> filteredList = new FilteredList<>(FXCollections.observableArrayList(t1.history.entries));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        filteredList.setPredicate(historyEntry -> historyEntry.date.after(cal.getTime()));
        tblHistory.setItems(filteredList);
    }

}
