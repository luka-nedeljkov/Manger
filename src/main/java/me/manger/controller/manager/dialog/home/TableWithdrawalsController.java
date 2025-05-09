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

import java.net.URL;
import java.util.ResourceBundle;

public class TableWithdrawalsController implements Initializable {

    @FXML
    private TableColumn<HistoryEntry, String> clmDate;
    @FXML
    private TableColumn<HistoryEntry, String> clmAmount;
    @FXML
    private TableColumn<HistoryEntry, String> clmMessage;
    @FXML
    private TableView<HistoryEntry> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
//        table.getItems().addAll(new HistoryEntry(10000.0, "Popravka svetla", null));
        table.getItems().addAll(Database.session.activeBuilding.history.entries);
    }

}
