package me.manger.controller.manager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.manger.model.building.Building;
import me.manger.model.Database;
import me.manger.model.ledger.LedgerEntry;

import java.net.URL;
import java.util.ResourceBundle;

public class LedgerController implements Initializable {

    @FXML
    private TableView<LedgerEntry> table;
    @FXML
    private TableColumn<LedgerEntry, String> clmDate;
    @FXML
    private TableColumn<LedgerEntry, String> clmMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Building building = Database.session.activeBuilding;

        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmMessage.setCellValueFactory(new PropertyValueFactory<>("message"));

        table.getItems().addAll(building.ledger.entries);
    }

}
