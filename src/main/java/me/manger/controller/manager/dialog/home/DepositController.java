package me.manger.controller.manager.dialog.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.manger.model.building.Building;
import me.manger.model.Database;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {

    @FXML
    private ChoiceBox<Property> chbSender;
    @FXML
    private TextField txfAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Building building = Database.session.activeBuilding;
        chbSender.getItems().addAll(building.properties);
        chbSender.getItems().addAll(building.garages);

        txfAmount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txfAmount.setText(newValue.replaceAll("\\D", ""));
            }
        });

        chbSender.getSelectionModel().selectFirst();
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) chbSender.getScene().getWindow()).close();
    }

    @FXML
    void deposit(ActionEvent event) {
        Manager manager = (Manager) Database.session.loggedIn;
        Property property = chbSender.getValue();
        Building building = property.building;
        double amount = Double.parseDouble(txfAmount.getText());

        building.deposit(amount);

        switch(property.type) {
            case "apartment" -> {
                building.ledger.addEntry("Uplata: " + amount + "din. - Stan " + property.number);
                manager.notifications.addEntry("Upravnik", "Uplata: " + amount + " din. - Stan " + property.number);
            }
            case "garage" -> {
                building.ledger.addEntry("Uplata: " + amount + "din. - Garaža " + property.number);
                manager.notifications.addEntry("Upravnik", "Uplata: " + amount + " din. - Garaža " + property.number);
            }
        }
        property.notifications.addEntry("Upravnik", "Uplata: " + amount + " din.");
        property.history.addEntry(amount, "Mesečna uplata", property);

        ((Stage) chbSender.getScene().getWindow()).close();
    }

}
