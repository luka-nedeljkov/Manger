package me.manger.controller.manager.dialog.home;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.manger.model.Database;
import me.manger.model.user.Property;
import me.manger.model.user.paymentHistory.HistoryEntry;
import me.manger.model.user.paymentHistory.HistoryHolder;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TableDepositsController implements Initializable {

    @FXML
    private TableColumn<HistoryEntry, String> clmAmount;
    @FXML
    private TableColumn<HistoryEntry, String> clmDate;
    @FXML
    private TableColumn<HistoryEntry, String> clmOwner;
    @FXML
    private TableColumn<HistoryEntry, Property> clmProperty;
    @FXML
    private TableView<HistoryEntry> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmOwner.setCellValueFactory(historyEntryStringCellDataFeatures ->
                new SimpleStringProperty(historyEntryStringCellDataFeatures.getValue().property.ownerNames));
        clmProperty.setCellValueFactory(new PropertyValueFactory<>("property"));
        for(Property property : Database.session.activeBuilding.properties) {
            table.getItems().addAll(property.history.entries);
        }
        for(Property property : Database.session.activeBuilding.garages) {
            table.getItems().addAll(property.history.entries);
        }
    }

}
