package me.manger.controller.manager.dialog.reclamation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import me.manger.model.Database;
import me.manger.model.building.Building;
import me.manger.model.building.ReclamationEntry;
import me.manger.model.company.Contractor;
import me.manger.model.company.Investor;
import me.manger.model.company.MainContractor;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateController implements Initializable {

    @FXML
    private ChoiceBox<Investor> chbInvestor;
    @FXML
    private ChoiceBox<MainContractor> chbMainContractor;
    @FXML
    private ChoiceBox<Contractor> chbContractor;
    @FXML
    private ChoiceBox<String> chbType;
    @FXML
    private ChoiceBox<Property> chbProperty;
    @FXML
    private TextArea txaMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chbInvestor.getItems().addAll(Database.investors);
        chbMainContractor.getItems().addAll(Database.mainContractors);
        chbContractor.getItems().addAll(Database.contractors);
        chbProperty.getItems().addAll(Database.session.activeBuilding.properties);
        chbProperty.getItems().addAll(Database.session.activeBuilding.garages);
        for(Contractor contractor : Database.contractors) {
            addUnique(chbType.getItems(), contractor.workType);
        }
        if(!chbType.getItems().isEmpty()) {
            chbType.getSelectionModel().select(0);
            filterList(chbType.getValue());
        }

        chbType.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            filterList(t1);
        });
    }

    public void filterList(String type) {
        ObservableList<Contractor> items = FXCollections.observableArrayList(Database.contractors);
        FilteredList<Contractor> filteredList = new FilteredList<>(items);
        filteredList.setPredicate(contractor -> contractor.workType.equals(type));
        chbContractor.setItems(filteredList);
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage) txaMessage.getScene().getWindow()).close();
    }

    @FXML
    void register(ActionEvent event) {
        Building building = Database.session.activeBuilding;
        Property property = chbProperty.getValue();

        building.reclamations.addEntry(
                txaMessage.getText(),
                chbInvestor.getValue(),
                chbMainContractor.getValue(),
                chbContractor.getValue(),
                chbProperty.getValue()
        );

        ((Manager) Database.session.loggedIn).notifications.addEntry("Upravnik", "Reklamacija registrovana - " + txaMessage.getText());
        property.notifications.addEntry("Upravnik", "Reklamacija registrovana - " + txaMessage.getText());
        building.ledger.addEntry("Reklamacija registrovana - " + txaMessage.getText());

        ((Stage) txaMessage.getScene().getWindow()).close();
    }

    private <T> void addUnique(List<T> list, T item) {
        if(!list.contains(item)) {
            list.add(item);
        }
    }

}
