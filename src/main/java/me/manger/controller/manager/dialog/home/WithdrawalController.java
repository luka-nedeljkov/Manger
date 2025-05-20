package me.manger.controller.manager.dialog.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import me.manger.model.Database;
import me.manger.model.building.Building;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;

import java.net.URL;
import java.util.ResourceBundle;

public class WithdrawalController implements Initializable {
    @FXML
    private Label lblBalance;
    @FXML
    private TextField txfAmount;
    @FXML
    private TextArea txaMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Building building = Database.session.activeBuilding;

        lblBalance.setText(building.balance + " din.");

        txfAmount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txfAmount.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) txfAmount.getScene().getWindow()).close();
    }

    @FXML
    void withdraw(ActionEvent event) {
        Building building = Database.session.activeBuilding;
        Manager manager = building.manager;

        if(txfAmount.getText().isBlank()) {
            (new Alert(Alert.AlertType.WARNING, "Unesite iznos novca za isplatu.", ButtonType.OK)).show();
            return;
        }

        double amount = Double.parseDouble(txfAmount.getText());
        String message = txaMessage.getText();

        if(txaMessage.getText().isBlank()) {
            (new Alert(Alert.AlertType.WARNING, "Unesite opis isplate.", ButtonType.OK)).show();
            return;
        }

        if(!building.withdraw(amount)) {
            return;
        }

        building.ledger.addEntry("Isplata: " + amount + " - " + message);
        manager.notifications.addEntry("Upravnik", "Isplata: " + amount + " - " + message);
        for(Property property : building.properties) {
            property.notifications.addEntry("Upravnik", "Isplata: " + amount + " - " + message);
        }
        for(Property property : building.garages) {
            property.notifications.addEntry("Upravnik", "Isplata: " + amount + " - " + message);
        }
        building.history.addEntry(amount, message, null);

        ((Stage) txfAmount.getScene().getWindow()).close();
    }

}
