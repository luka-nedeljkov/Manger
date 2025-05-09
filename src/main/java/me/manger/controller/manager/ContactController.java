package me.manger.controller.manager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import me.manger.model.Database;
import me.manger.model.company.Company;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactController implements Initializable {

    @FXML
    private Label lblCompanyName;
    @FXML
    private Label lblCompanyEmail;
    @FXML
    private Label lblCompanyPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Company company = Database.company;
        lblCompanyName.setText(company.name);
        lblCompanyEmail.setText(company.email);
        lblCompanyPhone.setText(company.phoneNumber);
    }

}
