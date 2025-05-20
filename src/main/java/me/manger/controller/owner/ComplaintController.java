package me.manger.controller.owner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import me.manger.model.Database;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;

public class ComplaintController {

    @FXML
    private TextArea txaMessage;

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) txaMessage.getScene().getWindow()).close();
    }

    @FXML
    void send(ActionEvent event) {
        String message = txaMessage.getText();
        if(message.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Poruka ne sme biti prazna.", ButtonType.OK);
            alert.show();
            return;
        }
        Property property = (Property) Database.session.loggedIn;
        Manager manager = property.building.manager;
        property.notifications.addEntry(property.toString(), message);
        manager.notifications.addEntry(property.toString(), message);
        ((Stage) txaMessage.getScene().getWindow()).close();
    }

}
