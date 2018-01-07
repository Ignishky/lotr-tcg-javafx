package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Name;
import fr.ducloyer.lotrtcg.core.model.Personage;
import fr.ducloyer.lotrtcg.core.utils.EndGameException;
import fr.ducloyer.lotrtcg.scene.LocatedImage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class CPersonage implements Initializable {

    private final static Image WOUND = new LocatedImage("/images/wound.png");

    @FXML
    private Node root;

    @FXML
    @Getter
    private CCard personageController;

    @FXML
    private TilePane wounds;

    @Getter
    private Personage abstraction;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void addPersonage(Name name) {
        addPersonage(name, 0);
    }

    public void addPersonage(Name name, int nbWounds) {
        abstraction = new Personage(name);
        personageController.addCard(name);
        for (int i = 0; i < nbWounds; i++) {
            addWound();
        }
        Toastr.append("Play card " + personageController.getName() + ".");
    }

    public void addWound() {
        abstraction.addWound();
        Platform.runLater(() -> wounds.getChildren().add(new ImageView(WOUND)));
        log.info("{} has been wounded", personageController.getName());
        Toastr.append(personageController.getName() + " has been wounded.");
    }

    public void kill() throws EndGameException {
        root.setVisible(false);
        log.info("{} has been killed", personageController.getName());
        if (personageController.getName().startsWith("Frodo")) {
            Toastr.append(personageController.getName() + " has been killed. YOU LOOSE !!!!");
            throw new EndGameException();
        } else {
            Toastr.append(personageController.getName() + " has been killed.");
        }
    }
}
