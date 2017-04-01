package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Personage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.google.common.collect.Lists.newArrayList;

@Slf4j
public class CPersonage extends Personage implements Initializable {

    @FXML
    private Node root;

    @FXML
    private CCard personageController;

    @FXML
    private ImageView woundOne;
    @FXML
    private ImageView woundTwo;
    @FXML
    private ImageView woundThree;
    @FXML
    private ImageView woundFour;

    private List<ImageView> wounds;

    public CPersonage() {
        super(null);
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        woundOne.setImage(new Image("/images/wound.png"));
        woundOne.setVisible(false);
        woundTwo.setImage(new Image("/images/wound.png"));
        woundTwo.setVisible(false);
        woundThree.setImage(new Image("/images/wound.png"));
        woundThree.setVisible(false);
        woundFour.setImage(new Image("/images/wound.png"));
        woundFour.setVisible(false);
        wounds = newArrayList(woundOne, woundTwo, woundThree, woundFour);
    }

    public void addPersonage(int collection) {
        super.addPersonage(personageController);
        personageController.addCard(collection);
        Toastr.append("Play card " + personageController.getName() + ".");
    }

    public void addWound() {
        wounds.get(nbWound).setVisible(true);
        super.addWound();
        log.info("{} has been wounded", personageController.getName());
        Toastr.append(personageController.getName() + " has been wounded.");
    }

    public void kill() {
        root.setVisible(false);
        log.info("{} has been killed", personageController.getName());
        Toastr.append(personageController.getName() + " has been killed.");
    }
}
