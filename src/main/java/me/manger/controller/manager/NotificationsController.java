package me.manger.controller.manager;

import javafx.beans.property.SimpleStringProperty;
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
import me.manger.model.building.Building;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;
import me.manger.model.user.notifications.NotificationEntry;

import java.net.URL;
import java.util.ArrayList;
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
		table.getItems().addAll(getEntries((Manager) Database.session.loggedIn).reversed());

		clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		clmSource.setCellValueFactory(notificationEntryStringCellDataFeatures -> {
			NotificationEntry entry = notificationEntryStringCellDataFeatures.getValue();
			Property property = Database.searchProperty(entry.source);
			if(property == null) {
				return new SimpleStringProperty(entry.source);
			}
			return new SimpleStringProperty(property.toString());
		});
		clmMessage.setCellValueFactory(new PropertyValueFactory<>("message"));

		filter.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
			ObservableList<NotificationEntry> items = FXCollections.observableArrayList(getEntries((Manager) Database.session.loggedIn).reversed());
			FilteredList<NotificationEntry> filteredList = new FilteredList<>(items);
			switch(((RadioButton) t1).getText()) {
				case "Obaveštenja" -> {
					filteredList.setPredicate(notificationEntry -> !notificationEntry.message.matches("Uplata: .+") &&
							!notificationEntry.message.matches("Isplata: .+") &&
							notificationEntry.source.matches("Upravnik"));
					table.setItems(filteredList);
				}
				case "Uplate" -> {
					filteredList.setPredicate(notificationEntry -> notificationEntry.message.matches("Uplata: .+"));
					table.setItems(filteredList);
				}
				case "Isplate" -> {
					filteredList.setPredicate(notificationEntry -> notificationEntry.message.matches("Isplata: .+"));
					table.setItems(filteredList);
				}
				case "Žalbe" -> {
					filteredList.setPredicate(notificationEntry -> !notificationEntry.source.matches("Upravnik"));
					table.setItems(filteredList);
				}
				case "Sve" -> {
					table.setItems(items);
				}
			}
		});
	}

	private ArrayList<NotificationEntry> getEntries(Manager manager) {
		Building building = Database.session.activeBuilding;
		ArrayList<NotificationEntry> entries = manager.notifications.entries;
		for(int i = 0; i < entries.size(); i++) {
			Property property = Database.searchProperty(entries.get(i).source);
			if(property == null) {
				continue;
			}
			if(!property.building.equals(building)) {
				entries.remove(entries.get(i));
			}
		}
		return entries;
	}

}
