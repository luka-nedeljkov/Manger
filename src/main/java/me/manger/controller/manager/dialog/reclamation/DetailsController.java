package me.manger.controller.manager.dialog.reclamation;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import me.manger.model.building.ReclamationEntry;

public class DetailsController {

    @FXML
    private Label lblCreated;
    @FXML
    private Label lblCompleted;
    @FXML
    private Label lblMessage;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblInvestor;
    @FXML
    private Label lblMainContractor;
    @FXML
    private Label lblContractor;
    @FXML
    private Label lblProperty;

    public void init(ReclamationEntry entry) {
        lblCreated.setText(entry.getDateCreated());
        lblCompleted.setText(entry.getDateCompleted());
        lblMessage.setText(entry.message);
        switch(entry.status) {
            case "reported" -> {
                lblStatus.setText("Prijavljeno");
            }
            case "inspected" -> {
                lblStatus.setText("Izvrsen uvid");
            }
            case "ongoing" -> {
                lblStatus.setText("Radovi u toku");
            }
            case "completed" -> {
                lblStatus.setText("Zavrseno");
            }
            default -> {
                lblStatus.setText("error");
            }
        }
        lblInvestor.setText(entry.investor.name);
        lblMainContractor.setText(entry.mainContractor.name);
        lblContractor.setText(entry.contractor.name);
        lblProperty.setText(entry.sourceProperty.toString());
    }

    @FXML
    void close(ActionEvent event) {
        ((Stage) lblCreated.getScene().getWindow()).close();
    }

}