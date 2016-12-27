package fr.ducloyer.lotrtcg.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssertContext;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.service.finder.NodeFinder;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.core.model.Card.Name.*;
import static org.hamcrest.Matchers.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CBoardTest extends ApplicationTest {

    private static final NodeFinder NODE_FINDER = new FxAssertContext().getNodeFinder();
    private FXMLLoader loader = new FXMLLoader();

    @Override
    public void start(Stage stage) throws IOException {
        loader.setLocation(getClass().getResource("/view/board.fxml"));
        Parent anchorPane = loader.load();
        CBoard cBoard = loader.getController();
        cBoard.addCompanion(Frodo);
        cBoard.addCompanion(Gandalf);
        cBoard.addMinion(GoblinMarksman);
        cBoard.addMinion(MoriaScout);
        stage.setScene(new Scene(anchorPane, 250, 350));
        stage.show();
    }

    @Test
    public void should_load_board() {
        AnchorPane companion1 = NODE_FINDER.lookup("#companion1").query();
        verifyThat(companion1.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(companion1.getChildren().get(1), isInvisible());

        AnchorPane companion2 = NODE_FINDER.lookup("#companion2").query();
        verifyThat(companion2.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(companion2.getChildren().get(1), isInvisible());

        AnchorPane minion1 = NODE_FINDER.lookup("#minion1").query();
        verifyThat(minion1.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(minion1.getChildren().get(1), isInvisible());

        AnchorPane minion2 = NODE_FINDER.lookup("#minion2").query();
        verifyThat(minion2.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(minion2.getChildren().get(1), isInvisible());

        Button fight = NODE_FINDER.lookup("#action").query();
        verifyThat(fight, isVisible());
        verifyThat(fight.getLayoutX(), equalTo(100.0));
        verifyThat(fight.getLayoutY(), equalTo(328.0));

        verifyThat(companion1.getLayoutY(), equalTo(companion2.getLayoutY()));
        verifyThat(companion1.getLayoutX(), equalTo(minion1.getLayoutX()));
        verifyThat(minion1.getLayoutY(), equalTo(minion2.getLayoutY()));
        verifyThat(companion2.getLayoutX(), equalTo(minion2.getLayoutX()));

        TextArea info = NODE_FINDER.lookup("#info").query();
        verifyThat(info.getText(), equalTo("Play card Frodo.\nPlay card Gandalf.\nPlay card Goblin Marksman.\nPlay card Moria Scout.\n"));
    }

    @Test
    public void should_wound_companion_for_the_fight() {
        clickOn("#action");

        AnchorPane companion1 = NODE_FINDER.lookup("#companion1").query();
        verifyThat(companion1.getChildren().get(1), isVisible());

        TextArea info = NODE_FINDER.lookup("#info").query();
        verifyThat(info.getText(), endsWith("Frodo has been wounded.\n"));

        clickOn("#action");

        AnchorPane minion2 = NODE_FINDER.lookup("#minion2").query();
        verifyThat(minion2.getChildren().get(1), isVisible());

        verifyThat(info.getText(), endsWith("Moria Scout has been wounded.\n"));
    }

    @Test
    public void should_end_turn_when_all_companion_have_fight() {
        Button fight = NODE_FINDER.lookup("#action").query();
        verifyThat(fight.getText(), is("Fight"));
        clickOn("#action");

        fight = NODE_FINDER.lookup("#action").query();
        verifyThat(fight.getText(), is("Fight"));
        clickOn("#action");

        fight = NODE_FINDER.lookup("#action").query();
        verifyThat(fight.getText(), is("Next turn"));
    }

    @Test
    public void should_rewound_first_companion_in_the_second_turn() {
        clickOn("#action"); // fight 1
        clickOn("#action"); // fight 2
        clickOn("#action"); // next turn
        clickOn("#action"); // fight 1

        AnchorPane companion1 = NODE_FINDER.lookup("#companion1").query();
        verifyThat(companion1.getChildren().get(2), isVisible());

        TextArea info = NODE_FINDER.lookup("#info").query();
        verifyThat(info.getText(), endsWith("Frodo has been wounded.\n"));
    }
}