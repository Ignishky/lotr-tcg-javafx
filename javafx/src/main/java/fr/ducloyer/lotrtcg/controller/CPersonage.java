package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Personage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class CPersonage extends Personage implements Initializable {

    @FXML
    private CCard personageController;

    @FXML
    private ImageView oneWound;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        oneWound.setImage(new Image("/images/wound.png"));
    }

    public void addPersonage(int collection) {
        super.addPersonage(personageController);
        personageController.addCard(collection);
        Toastr.append("Play card " + getName() + ".");
    }

    public void addWound() {
        log.info("{} has been wounded", getName());
        super.addWound();
        oneWound.setVisible(true);
        Toastr.append(getName() + " has been wounded.");
    }
}
