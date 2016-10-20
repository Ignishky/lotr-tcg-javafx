package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CCard extends Card implements Initializable {

    @FXML
    private ImageView card;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        addCard(0);
    }

    public void addCard(int number) {
        super.copy(number);
        card.setImage(new Image(picture));
    }
}
