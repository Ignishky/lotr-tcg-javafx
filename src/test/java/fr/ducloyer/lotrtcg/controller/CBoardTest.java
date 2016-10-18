package fr.ducloyer.lotrtcg.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssertContext;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.service.finder.NodeFinder;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CBoardTest extends ApplicationTest {

    private static final NodeFinder NODE_FINDER = new FxAssertContext().getNodeFinder();
    private FXMLLoader loader = new FXMLLoader();

    @Override
    public void start(Stage stage) throws IOException {
        loader.setLocation(getClass().getResource("/view/board.fxml"));
        Parent anchorPane = loader.load();
        stage.setScene(new Scene(anchorPane, 250, 350));
        stage.show();
    }

    @Test
    public void should_load_board() {
        AnchorPane companion1 = NODE_FINDER.lookup("#companion1").queryFirst();
        verifyThat(companion1.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(companion1.getChildren().get(1), isInvisible());

        AnchorPane companion2 = NODE_FINDER.lookup("#companion2").queryFirst();
        verifyThat(companion2.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(companion2.getChildren().get(1), isInvisible());

        AnchorPane minion1 = NODE_FINDER.lookup("#minion1").queryFirst();
        verifyThat(minion1.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(minion1.getChildren().get(1), isInvisible());

        AnchorPane minion2 = NODE_FINDER.lookup("#minion2").queryFirst();
        verifyThat(minion2.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(minion2.getChildren().get(1), isInvisible());

        Button fight = NODE_FINDER.lookup("#fight").queryFirst();
        verifyThat(fight, isVisible());
        verifyThat(fight.getLayoutX(), equalTo(92.0));
        verifyThat(fight.getLayoutY(), equalTo(338.0));

        verifyThat(companion1.getLayoutY(), equalTo(companion2.getLayoutY()));
        verifyThat(companion1.getLayoutX(), equalTo(minion1.getLayoutX()));
        verifyThat(minion1.getLayoutY(), equalTo(minion2.getLayoutY()));
        verifyThat(companion2.getLayoutX(), equalTo(minion2.getLayoutX()));

        Label info = NODE_FINDER.lookup("#info").queryFirst();
        verifyThat(info, hasText("Play card Frodo"));
    }

    @Test
    public void should_wound_companion_for_the_fight() {
        clickOn("#fight");

        AnchorPane companion1 = NODE_FINDER.lookup("#companion1").queryFirst();
        verifyThat(companion1.getChildren().get(1), isVisible());

        Label info = NODE_FINDER.lookup("#info").queryFirst();
        verifyThat(info, hasText("Frodo has been wounded"));
    }
}