package me.manger.controller.owner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import me.manger.model.building.Building;
import me.manger.model.Database;
import me.manger.model.Session;
import me.manger.model.company.Company;
import me.manger.model.user.Property;
import me.manger.model.user.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label lblAddress;
    @FXML
    private Label lblNumber;
    @FXML
    private Label lblArea;

    @FXML
    private Label lblNames;
    @FXML
    private Label lblEmails;
    @FXML
    private Label lblPhones;

    @FXML
    private Label lblManagerName;
    @FXML
    private Label lblManagerEmail;
    @FXML
    private Label lblManagerPhone;

    @FXML
    private Label lblCompanyName;
    @FXML
    private Label lblCompanyEmail;
    @FXML
    private Label lblCompanyPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Session session = Database.session;
        Property property = (Property) session.loggedIn;
        Building building = property.building;
        Manager manager = building.manager;
        Company company = Database.company;

        lblAddress.setText(property.building.address);
        lblNumber.setText(String.valueOf(property.number));
        lblArea.setText(property.area + " m2");

        lblNames.setText(property.ownerNames);
        lblEmails.setText(property.ownerEmails);
        lblPhones.setText(property.ownerPhones);

        lblManagerName.setText(manager.name);
        lblManagerEmail.setText(manager.email);
        lblManagerPhone.setText(manager.phone);

        lblCompanyName.setText(company.name);
        lblCompanyEmail.setText(company.email);
        lblCompanyPhone.setText(company.phoneNumber);
    }

    @FXML
    void sendComplaint(ActionEvent event) throws IOException {
        Stage complaintStage = new Stage();
        complaintStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/me/manger/view/owner/Complaint.fxml"))));
        complaintStage.setResizable(false);
        complaintStage.setTitle("Posalji zalbu");
        complaintStage.show();
    }

}
