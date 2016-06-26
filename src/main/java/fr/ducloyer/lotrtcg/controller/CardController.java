package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Card;
import fr.ducloyer.lotrtcg.model.CardLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public void initialize(URL url, ResourceBundle rb) {
        displayCard(0);
    }

    public void displayCard(int number) {
        Card aCard = LOADER.loadCard(number);
        card.setImage(new Image(getClass().getResourceAsStream(aCard.getPicture())));
    }
}
