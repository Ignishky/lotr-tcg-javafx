package fr.ducloyer.lotrtcg.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import fr.ducloyer.lotrtcg.model.CardLoader;

public class BoardController implements Initializable {

    private static final CardLoader LOADER = CardLoader.getInstance();

    @FXML
    private ImageView companion1;

    @FXML
    private ImageView companion2;

    @FXML
    private ImageView minion1;

    @FXML
    private ImageView minion2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        companion1.setImage(new Image(getClass().getResourceAsStream(LOADER.loadCard(1290).getPicture())));
        companion2.setImage(new Image(getClass().getResourceAsStream(LOADER.loadCard(1364).getPicture())));
        minion1.setImage(new Image(getClass().getResourceAsStream(LOADER.loadCard(1176).getPicture())));
        minion2.setImage(new Image(getClass().getResourceAsStream(LOADER.loadCard(1191).getPicture())));
    }
}
