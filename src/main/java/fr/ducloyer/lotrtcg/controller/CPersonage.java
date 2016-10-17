package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Card;
import fr.ducloyer.lotrtcg.model.Personage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CPersonage implements Initializable {

    @FXML
    private CCard personageController;

    @FXML
    private Label oneWound;

    private Personage aPersonage;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        oneWound.setVisible(false);
    }

    public Personage addPersonage(int collection) {
        Card personage = personageController.addCard(collection);
        aPersonage = new Personage(personage);
        return aPersonage;
    }

    public Personage addWound() {
        aPersonage.addWound();
        oneWound.setVisible(true);
        return aPersonage;
    }
}
