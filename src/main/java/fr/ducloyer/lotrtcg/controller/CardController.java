package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Card;
import fr.ducloyer.lotrtcg.model.CardLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Data;

import java.net.URL;
import java.util.ResourceBundle;

@Data
public class CardController implements Initializable {

    private static final CardLoader LOADER = CardLoader.getInstance();

    @FXML
    private ImageView card;

    @FXML
    private Label oneWound;

    private Card aCard;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        aCard = addCard(0);
    }

    public Card addCard(int number) {
        oneWound.setVisible(false);
        Card aCard = LOADER.loadCard(number);
        card.setImage(new Image(getClass().getResourceAsStream(aCard.getPicture())));
        this.aCard = aCard;
        return aCard;
    }

    public Card addWound() {
        oneWound.setVisible(true);
        return aCard;
    }
}
