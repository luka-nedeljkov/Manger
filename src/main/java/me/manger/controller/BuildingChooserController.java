package me.manger.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import me.manger.MangerApplication;
import me.manger.model.building.Building;
import me.manger.model.Database;
import me.manger.model.user.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuildingChooserController implements Initializable {

    @FXML
    private ChoiceBox<Building> chbBuilding;
    @FXML
    private Label lblError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chbBuilding.getItems().addAll(((Manager) Database.session.loggedIn).buildings);
        chbBuilding.getSelectionModel().select(0);
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Database.session = null;
        ((Stage) chbBuilding.getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(MangerApplication.class.getResource("/me/manger/view/Login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Manger");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void choose(ActionEvent event) throws IOException {
        if(chbBuilding.getItems().isEmpty()) {
            return;
        }

        Database.session.activeBuilding = chbBuilding.getValue();
        ((Stage) chbBuilding.getScene().getWindow()).close();
        FXMLLoader fxmlLoader = new FXMLLoader(MangerApplication.class.getResource("/me/manger/view/manager/Manager.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Manger");
        stage.setScene(scene);
        stage.show();
    }

}
