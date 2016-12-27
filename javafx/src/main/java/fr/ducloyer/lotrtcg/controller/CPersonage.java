package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Personage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        woundOne.setImage(new Image("/images/wound.png"));
        woundTwo.setImage(new Image("/images/wound.png"));
        woundThree.setImage(new Image("/images/wound.png"));
        woundFour.setImage(new Image("/images/wound.png"));

        wounds = newArrayList(woundOne, woundTwo, woundThree, woundFour);
    }

    public void addPersonage(int collection) {
        super.addPersonage(personageController);
        personageController.addCard(collection);
        Toastr.append("Play card " + getName() + ".");
    }

    public void addWound() {
        wounds.get(nbWound).setVisible(true);
        super.addWound();

        log.info("{} has been wounded", getName());
        Toastr.append(getName() + " has been wounded.");
    }
}
