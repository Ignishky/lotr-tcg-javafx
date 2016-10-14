package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    @FXML
    private CardController companion1Controller;

    @FXML
    private CardController companion2Controller;

    @FXML
    private CardController minion1Controller;

    @FXML
    private CardController minion2Controller;

    @FXML
    private Button fight;

    @FXML
    private Label info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Card card = companion1Controller.addCard(1290);
        info.setText("Play card " + card.getName());
        companion2Controller.addCard(1364);
        minion1Controller.addCard(1176);
        minion2Controller.addCard(1191);
    }

    @FXML
    public void startFight() {
        Card card = companion1Controller.addWound();
        info.setText(card.getName() + " has been wounded");
    }
}
