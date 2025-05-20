package me.manger.controller.manager.dialog.home;

import com.browniebytes.javafx.control.DateTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import me.manger.model.Database;
import me.manger.model.building.Building;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MeetingController {

    @FXML
    private DateTimePicker dtpDate;
    @FXML
    private TextArea txaSubject;

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) txaSubject.getScene().getWindow()).close();
    }

    @FXML
    void scheduleMeeting(ActionEvent event) {
        Date date = dtpDate.getTime();
        if(new Date().after(date)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vreme sastanka ne sme biti pre trenutnog.", ButtonType.OK);
            alert.show();
            return;
        }

        Building building = Database.session.activeBuilding;
        Manager manager = building.manager;

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
        String subject = txaSubject.getText();

        if(subject.isBlank()) {
            (new Alert(Alert.AlertType.WARNING, "Unesite temu dnevni red sastanka.", ButtonType.OK)).show();
            return;
        }

        building.ledger.addEntry("Sastanak: " + sdf.format(date) + " - Dnevni red: " + subject);
        manager.notifications.addEntry("Upravnik", "Sastanak: " + sdf.format(date) + " - Dnevni red: " + subject);
        for(Property property : building.properties) {
            property.notifications.addEntry("Upravnik", "Sastanak: " + sdf.format(date) + " - Dnevni red: " + subject);
        }
        for(Property property : building.garages) {
            property.notifications.addEntry("Upravnik", "Sastanak: " + sdf.format(date) + " - Dnevni red: " + subject);
        }

        ((Stage) txaSubject.getScene().getWindow()).close();
    }

}
