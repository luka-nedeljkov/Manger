package me.manger.controller.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.manger.MangerApplication;
import me.manger.model.Database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {

	@FXML
	private Button btnH;
	@FXML
	private Button btnC;
	@FXML
	private Button btnN;
	@FXML
	private Button btnL;
	@FXML
	private Button btnR;

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
		btnH.setDisable(true);
		btnC.setDisable(false);
		btnN.setDisable(false);
		btnL.setDisable(false);
		btnR.setDisable(false);
	}

	@FXML
	void contact(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Contact.fxml")));
		btnH.setDisable(false);
		btnC.setDisable(true);
		btnN.setDisable(false);
		btnL.setDisable(false);
		btnR.setDisable(false);
	}

	@FXML
	void notifications(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Notifications.fxml")));
		btnH.setDisable(false);
		btnC.setDisable(false);
		btnN.setDisable(true);
		btnL.setDisable(false);
		btnR.setDisable(false);
	}

	@FXML
	void ledger(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Ledger.fxml")));
		btnH.setDisable(false);
		btnC.setDisable(false);
		btnN.setDisable(false);
		btnL.setDisable(true);
		btnR.setDisable(false);
	}

	@FXML
	void reclamation(ActionEvent event) throws IOException {
		root.getChildren().set(0, FXMLLoader.load(getClass().getResource("/me/manger/view/manager/Reclamation.fxml")));
		btnH.setDisable(false);
		btnC.setDisable(false);
		btnN.setDisable(false);
		btnL.setDisable(false);
		btnR.setDisable(true);
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
