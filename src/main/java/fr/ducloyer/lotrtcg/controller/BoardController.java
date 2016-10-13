package fr.ducloyer.lotrtcg.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import fr.ducloyer.lotrtcg.model.CardLoader;
import javafx.scene.layout.AnchorPane;

public class BoardController implements Initializable {

    private static final CardLoader LOADER = CardLoader.getInstance();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companion1Controller.displayCard(1290);
        companion2Controller.displayCard(1364);
        minion1Controller.displayCard(1176);
        minion2Controller.displayCard(1191);
    }

    @FXML
    public void startFight() {
        companion1Controller.displayWound();
        companion2Controller.displayWound();
    }
}
