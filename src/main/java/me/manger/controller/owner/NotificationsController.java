package me.manger.controller.owner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import me.manger.model.Database;
import me.manger.model.user.Property;
import me.manger.model.user.notifications.NotificationEntry;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class NotificationsController implements Initializable {

    @FXML
    private ToggleGroup filter;

    @FXML
    private TableView<NotificationEntry> table;
    @FXML
    private TableColumn<NotificationEntry, String> clmDate;
    @FXML
    private TableColumn<NotificationEntry, String> clmMessage;
    @FXML
    private TableColumn<NotificationEntry, String> clmSource;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        clmMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        table.getItems().addAll(((Property) Database.session.loggedIn).notifications.entries);

        filter.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            ObservableList<NotificationEntry> items = FXCollections.observableArrayList(((Property) Database.session.loggedIn).notifications.entries);
            FilteredList<NotificationEntry> filteredList = new FilteredList<>(items);
            switch(((RadioButton) t1).getText()) {
                case "Obavestenja" -> {
                    filteredList.setPredicate(notificationEntry -> !notificationEntry.message.matches("Uplata: .+") && !notificationEntry.source.matches("Garaza \\d+|Stan \\d+"));
                    table.setItems(filteredList);
                }
                case "Uplate" -> {
                    filteredList.setPredicate(notificationEntry -> notificationEntry.message.matches("Uplata: .+"));
                    table.setItems(filteredList);
                }
                case "Zalbe" -> {
                    filteredList.setPredicate(notificationEntry -> notificationEntry.source.matches("Garaza \\d+|Stan \\d+"));
                    table.setItems(filteredList);
                }
                case "Sve" -> {
                    table.setItems(items);
                }
            }
        });
    }

}
