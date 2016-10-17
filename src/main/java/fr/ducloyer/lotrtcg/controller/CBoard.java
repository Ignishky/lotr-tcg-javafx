package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Personage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CBoard implements Initializable {

    @FXML
    private CPersonage companion1Controller;

    @FXML
    private CPersonage companion2Controller;

    @FXML
    private CPersonage minion1Controller;

    @FXML
    private CPersonage minion2Controller;

    @FXML
    private Button fight;

    @FXML
    private Label info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Personage personage = companion1Controller.addPersonage(1290);
        info.setText("Play card " + personage.getName());
        companion2Controller.addPersonage(1364);
        minion1Controller.addPersonage(1176);
        minion2Controller.addPersonage(1191);
    }

    @FXML
    public void startFight() {
        Personage personage = companion1Controller.addWound();
        info.setText(personage.getName() + " has been wounded");
    }
}
