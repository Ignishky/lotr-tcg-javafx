package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Personage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CPersonage extends Personage implements Initializable {

    @FXML
    private CCard personageController;

    @FXML
    private Label oneWound;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        oneWound.setVisible(false);
    }

    public void addPersonage(int collection) {
        super.addPersonage(personageController);
        personageController.addCard(collection);
    }

    public void addWound() {
        super.addWound();
        oneWound.setVisible(true);
    }
}
