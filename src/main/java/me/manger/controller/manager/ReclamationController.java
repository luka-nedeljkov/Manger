package me.manger.controller.manager;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import me.manger.controller.manager.dialog.reclamation.DetailsController;
import me.manger.controller.manager.dialog.reclamation.EditController;
import me.manger.model.Database;
import me.manger.model.building.ReclamationEntry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReclamationController implements Initializable {

    @FXML
    private TableView<ReclamationEntry> table;
    @FXML
    private TableColumn<ReclamationEntry, String> clmCreated;
    @FXML
    private TableColumn<ReclamationEntry, String> clmCompleted;
    @FXML
    private TableColumn<ReclamationEntry, String> clmStatus;
    @FXML
    private TableColumn<ReclamationEntry, String> clmProperty;
    @FXML
    private TableColumn<ReclamationEntry, String> clmMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setItems(FXCollections.observableArrayList(Database.session.activeBuilding.reclamations.entries.reversed()));
        clmCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        clmCompleted.setCellValueFactory(new PropertyValueFactory<>("dateCompleted"));
        clmStatus.setCellValueFactory(notificationEntryStringCellDataFeatures -> {
            ReclamationEntry entry = notificationEntryStringCellDataFeatures.getValue();
            switch(entry.status) {
                case "reported" -> {
                    return new SimpleStringProperty("Prijavljeno");
                }
                case "inspected" -> {
                    return  new SimpleStringProperty("Izvrsen uvid");
                }
                case "ongoing" -> {
                    return new SimpleStringProperty("Radovi u toku");
                }
                case "completed" -> {
                    return new SimpleStringProperty("Zavrseno");
                }
                default -> {
                    return new SimpleStringProperty("error");
                }
            }
        });
        clmProperty.setCellValueFactory(new PropertyValueFactory<>("sourceProperty"));
        clmMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
    }

    @FXML
    void create(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/me/manger/view/manager/dialog/reclamation/Create.fxml"))));
        stage.setResizable(false);
        stage.setTitle("Registruj reklamaciju");
        stage.showAndWait();
        table.getItems().clear();
        table.getItems().addAll(Database.session.activeBuilding.reclamations.entries.reversed());
    }

    @FXML
    void editStatus(ActionEvent event) throws IOException {
        if(table.getSelectionModel().getSelectedItem() == null) {
            (new Alert(Alert.AlertType.WARNING, "Morate odabrati reklamaciju da bi ste izmenili status.", ButtonType.OK)).show();
            return;
        }
        if(table.getSelectionModel().getSelectedItem().status.equals("completed")) {
            (new Alert(Alert.AlertType.WARNING, "Reklamacija je zatvorena.", ButtonType.OK)).show();
            return;
        }

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/me/manger/view/manager/dialog/reclamation/Edit.fxml")));
        stage.setScene(new Scene(loader.load()));
        ((EditController) loader.getController()).init(table.getSelectionModel().getSelectedItem());

        stage.setResizable(false);
        stage.setTitle("Izmeni status");
        stage.showAndWait();
        table.getItems().clear();
        table.getItems().addAll(Database.session.activeBuilding.reclamations.entries.reversed());
    }

    @FXML
    void details(ActionEvent event) throws IOException {
        if(table.getSelectionModel().getSelectedItem() == null) {
            (new Alert(Alert.AlertType.WARNING, "Morate odabrati reklamaciju da bi ste videli detalje.", ButtonType.OK)).show();
            return;
        }

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader((getClass().getResource("/me/manger/view/manager/dialog/reclamation/Details.fxml")));
        stage.setScene(new Scene(loader.load()));
        ((DetailsController) loader.getController()).init(table.getSelectionModel().getSelectedItem());

        stage.setResizable(false);
        stage.setTitle("Detalji o reklamaciji");
        stage.show();
    }

}
