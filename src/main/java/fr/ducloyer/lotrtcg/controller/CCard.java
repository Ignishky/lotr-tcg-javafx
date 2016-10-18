package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Card;
import fr.ducloyer.lotrtcg.model.CardLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CCard extends Card implements Initializable {

    private static final CardLoader LOADER = CardLoader.getInstance();

    @FXML
    private ImageView card;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        addCard(0);
    }

    public CCard addCard(int number) {
        super.copy(LOADER.loadCard(number));
        card.setImage(new Image(getClass().getResourceAsStream(getPicture())));
        return this;
    }
}
