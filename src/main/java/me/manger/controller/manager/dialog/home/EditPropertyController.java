package me.manger.controller.manager.dialog.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.manger.model.Database;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;

public class EditPropertyController {

    private Property property;

    @FXML
    private TextField txfName;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfPhone;

    public void init(Property property) {
        this.property = property;
        txfName.setText(property.ownerNames);
        txfEmail.setText(property.ownerEmails);
        txfPhone.setText(property.ownerPhones);
    }

    @FXML
    void submit(ActionEvent event) {
        property.ownerNames = txfName.getText();
        property.ownerEmails = txfEmail.getText();
        property.ownerPhones = txfPhone.getText();

        property.notifications.addEntry("Upravnik", "Izmenjeni podaci o vlasniku: " + property);
        ((Manager) Database.session.loggedIn).notifications.addEntry("Upravnik", "Izmenjeni podaci o vlasniku: " + property);
        Database.session.activeBuilding.ledger.addEntry("Izmenjeni podaci o vlasniku: " + property);

        ((Stage) txfName.getScene().getWindow()).close();
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) txfName.getScene().getWindow()).close();
    }

}
