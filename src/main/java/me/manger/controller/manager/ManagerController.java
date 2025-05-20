package me.manger.controller.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.manger.MangerApplication;
import me.manger.model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {

	@FXML
	private AnchorPane root;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			root.getChildren().add(FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Home.fxml")));
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	void home(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Home.fxml")));
    }

	@FXML
	void contact(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Contact.fxml")));
    }

	@FXML
	void notifications(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Notifications.fxml")));
    }

	@FXML
	void ledger(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Ledger.fxml")));
    }

	@FXML
	void reclamation(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Reclamation.fxml")));
    }

	@FXML
	void logout(ActionEvent event) throws IOException {
		Database.session = null;
		((Stage) root.getScene().getWindow()).close();
		FXMLLoader fxmlLoader = new FXMLLoader(MangerApplication.class.getResource("/me/manger/view/Login.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(fxmlLoader.load());
		stage.setResizable(false);
		stage.setTitle("Manger");
		stage.setScene(scene);
		stage.show();
	}

}
