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
import java.util.Set;

import static fr.ducloyer.lotrtcg.core.model.Card.Name.*;
import static org.hamcrest.Matchers.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CBoardTest extends ApplicationTest {

    private static final NodeFinder NODE_FINDER = new FxAssertContext().getNodeFinder();
    private final FXMLLoader loader = new FXMLLoader();

    private AnchorPane companion1;
    private AnchorPane companion2;
    private AnchorPane minion1;
    private AnchorPane minion2;
    private Button fight;
    private TextArea info;

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

        Set<AnchorPane> personages = NODE_FINDER.lookup("#root").queryAll();
        companion1 = personages.stream().filter(p -> p.getLayoutX() == 15.0 && p.getLayoutY() == 165.0).findFirst().get();
        companion2 = personages.stream().filter(p -> p.getLayoutX() == 125.0 && p.getLayoutY() == 165.0).findFirst().get();
        minion1 = personages.stream().filter(p -> p.getLayoutX() == 15.0 && p.getLayoutY() == 15.0).findFirst().get();
        minion2 = personages.stream().filter(p -> p.getLayoutX() == 125.0 && p.getLayoutY() == 15.0).findFirst().get();
        fight = NODE_FINDER.lookup("#action").query();
        info = NODE_FINDER.lookup("#info").query();
    }

    @Test
    public void should_load_board() {

        verifyPersonageInit(companion1);
        verifyPersonageInit(companion2);
        verifyPersonageInit(minion1);
        verifyPersonageInit(minion2);

        verifyThat(fight, isVisible());
        verifyThat(fight.getLayoutX(), equalTo(100.0));
        verifyThat(fight.getLayoutY(), equalTo(328.0));

        verifyThat(info.getText(), equalTo("Play card Frodo.\nPlay card Gandalf.\nPlay card Goblin Marksman.\nPlay card Moria Scout.\n"));
    }

    @Test
    public void should_wound_companion_for_the_fight() {
        clickOn("#action");

        verifyThat(companion1.getChildren().get(1), isVisible());
        verifyThat(info.getText(), endsWith("Frodo has been wounded.\n"));

        clickOn("#action");

        verifyThat(minion2.getChildren().get(1), isVisible());
        verifyThat(info.getText(), endsWith("Moria Scout has been wounded.\n"));
    }

    @Test
    public void should_end_turn_when_all_companion_have_fight() {
        verifyThat(fight.getText(), is("Fight"));
        clickOn("#action");

        verifyThat(fight.getText(), is("Fight"));
        clickOn("#action");

        verifyThat(fight.getText(), is("Next turn"));
    }

    @Test
    public void should_rewound_first_companion_in_the_second_turn() {
        clickOn("#action"); // fight 1
        clickOn("#action"); // fight 2
        clickOn("#action"); // next turn
        clickOn("#action"); // fight 1

        verifyThat(companion1.getChildren().get(2), isVisible());
        verifyThat(info.getText(), endsWith("Frodo has been wounded.\n"));
    }

    @Test
    public void should_kill_scout_after_two_wounds() {
        clickOn("#action"); // fight 1
        clickOn("#action"); // fight 2
        clickOn("#action"); // next turn
        clickOn("#action"); // fight 1
        clickOn("#action"); // fight 2

        verifyThat(minion2, isInvisible());
        verifyThat(info.getText(), endsWith("Moria Scout has been killed.\n"));
    }

    private void verifyPersonageInit(AnchorPane personage) {
        verifyThat(personage.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(personage.getChildren().get(1), isInvisible());
        verifyThat(personage.getChildren().get(2), isInvisible());
        verifyThat(personage.getChildren().get(3), isInvisible());
        verifyThat(personage.getChildren().get(4), isInvisible());
    }
}