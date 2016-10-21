package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CPersonageTest extends ApplicationTest {

    private FXMLLoader loader = new FXMLLoader();

    @Override
    public void start(Stage stage) throws IOException {
        loader.setLocation(getClass().getResource("/view/personage.fxml"));
        Parent anchorPane = loader.load();
        stage.setScene(new Scene(anchorPane, 109, 150));
        stage.show();
    }

    @Test
    public void should_not_display_wound_after_init() {
        verifyThat("#oneWound", isInvisible());
    }

    @Test
    public void should_init_personage() {

        CPersonage controller = loader.getController();
        controller.addPersonage(1364);

        verifyThat(controller.getName(), equalTo(controller.getPersonage().getName()));
        verifyThat(controller.getNbWound(), equalTo(0));
        verifyThat(controller.getPersonage(), equalTo(new Card(1364, "Gandalf", "/card/Fellowship/LOTR-EN01364.jpg", 7)));
        verifyThat("#oneWound", isInvisible());
    }

    @Test
    public void should_add_wound() {
        CPersonage controller = loader.getController();
        controller.addPersonage(1364);
        controller.addWound();

        verifyThat("#oneWound", isVisible());
    }
}