package me.manger.controller.manager.dialog.reclamation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import me.manger.model.building.ReclamationEntry;

import java.util.Date;

public class EditController {

    private ReclamationEntry entry;

    @FXML
    private ChoiceBox<String> chbStatus;

    public void init(ReclamationEntry entry) {
        chbStatus.getItems().addAll(
                "Prijavljeno",
                "Izvrsen uvid",
                "Radovi u toku",
                "Zavrseno"
        );
        this.entry = entry;
        chbStatus.getSelectionModel().select(map(entry.status));
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) chbStatus.getScene().getWindow()).close();
    }

    @FXML
    void submit(ActionEvent event) {
        String status = map(chbStatus.getValue());
        if(status.equals("completed")) {
            entry.dateCompleted = new Date();
        }
        entry.status = status;
        ((Stage) chbStatus.getScene().getWindow()).close();
    }

    private String map(String status) {
        switch(status) {
            case "Prijavljeno" -> {
                return "reported";
            }
            case "Izvrsen uvid" -> {
                return "inspected";
            }
            case "Radovi u toku" -> {
                return "ongoing";
            }
            case "Zavrseno" -> {
                return "completed";
            }

            case "reported" -> {
                return "Prijavljeno";
            }
            case "inspected" -> {
                return "Izvrsen uvid";
            }
            case "ongoing" -> {
                return "Radovi u toku";
            }
            case "completed" -> {
                return "Zavrseno";
            }

            default -> {
                return "error";
            }
        }
    }

}
