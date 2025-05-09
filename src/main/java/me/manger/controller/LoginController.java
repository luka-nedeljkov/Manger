package me.manger.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.manger.model.Database;
import me.manger.model.Session;
import me.manger.model.user.Manager;
import me.manger.model.user.Property;
import me.manger.model.user.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label errorMessage;
    @FXML
    private PasswordField pwfPassword;
    @FXML
    private TextField txfUsername;

    @FXML
    void login(ActionEvent event) throws IOException {
        User user = Database.searchUser(txfUsername.getText());
        if(user == null) {
            return;
        }

        FXMLLoader fxmlLoader;

        if(user instanceof Property) {
            if(!pwfPassword.getText().equals(((Property) user).password)) {
                errorMessage.setText("Invalid username or password");
                return;
            }
            fxmlLoader = new FXMLLoader(getClass().getResource("/me/manger/view/owner/Owner.fxml"));
        } else {
            if(!pwfPassword.getText().equals(((Manager) user).password)) {
                errorMessage.setText("Invalid username or password");
                return;
            }
            fxmlLoader = new FXMLLoader(getClass().getResource("/me/manger/view/BuildingChooser.fxml"));
        }

        ((Stage) errorMessage.getScene().getWindow()).close();

        Database.session = new Session(user);

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Manger");
        stage.setScene(scene);
        stage.show();
    }

}