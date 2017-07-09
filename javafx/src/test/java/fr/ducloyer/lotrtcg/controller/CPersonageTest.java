package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.controller.CCard.MIN_HEIGHT;
import static fr.ducloyer.lotrtcg.controller.CCard.MIN_WIDTH;
import static fr.ducloyer.lotrtcg.core.model.Card.Name.Gandalf;
import static fr.ducloyer.lotrtcg.core.model.Card.Side.FREE_PEOPLE;
import static org.hamcrest.Matchers.equalTo;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CPersonageTest extends AbstractControllerTest {

    private final FXMLLoader loader = new FXMLLoader();

    @Override
    public void start(Stage stage) throws IOException {
        loader.setLocation(getClass().getResource("/view/personage.fxml"));
        Parent anchorPane = loader.load();
        stage.setScene(new Scene(anchorPane, MIN_WIDTH, MIN_HEIGHT));
        stage.show();
    }

    @Test
    public void should_not_display_wound_after_init() {
        verifyThat("#woundOne", isInvisible());
    }

    @Test
    public void should_init_personage() {

        CPersonage controller = loader.getController();
        controller.addPersonage(Gandalf.getCollection());

        verifyThat(controller.getNbWound(), equalTo(0));
        verifyThat(controller.getPersonage(), equalTo(new Card(Gandalf.getCollection(), FREE_PEOPLE, "Gandalf", "/card/Fellowship/LOTR-EN01364.jpg", 7, 4)));
        verifyThat("#woundOne", isInvisible());
    }

    @Test
    public void should_add_wound() {
        CPersonage controller = loader.getController();
        controller.addPersonage(Gandalf.getCollection());
        controller.addWound();
        controller.addWound();
        controller.addWound();

        verifyThat("#woundOne", isVisible());
        verifyThat("#woundTwo", isVisible());
        verifyThat("#woundThree", isVisible());
    }
}