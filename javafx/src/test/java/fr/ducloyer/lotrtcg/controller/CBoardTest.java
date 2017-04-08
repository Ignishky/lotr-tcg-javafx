package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Card.Name;
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

        initPersonages(Frodo, 0, Gandalf, 0, GoblinMarksman, 0, MoriaScout,0);

        verifyPersonage(companion1, false, false, false, false);
        verifyPersonage(companion2, false, false, false, false);
        verifyPersonage(minion1, false, false, false, false);
        verifyPersonage(minion2, false, false, false, false);

        verifyThat(fight, isVisible());
        verifyThat(fight.getLayoutX(), equalTo(100.0));
        verifyThat(fight.getLayoutY(), equalTo(328.0));

        verifyThat(info.getText(), equalTo("Play card Frodo.\nPlay card Gandalf.\nPlay card Goblin Marksman.\nPlay card Moria Scout.\n"));
    }

    @Test
    public void should_wound_personage_for_the_fight() {

        initPersonages(Frodo, 0, Gandalf, 0, GoblinMarksman, 0, MoriaScout,0);

        clickOn("#action");

        verifyPersonage(companion1, true, false, false, false);
        verifyThat(info.getText(), endsWith("Frodo has been wounded.\n"));

        clickOn("#action");

        verifyPersonage(minion2, true, false, false, false);
        verifyThat(info.getText(), endsWith("Moria Scout has been wounded.\n"));
    }

    @Test
    public void should_end_turn_when_all_companion_have_fight() {

        initPersonages(Frodo, 0, Gandalf, 0, GoblinMarksman, 0, MoriaScout,0);

        verifyThat(fight.getText(), is("Fight"));
        clickOn("#action");

        verifyThat(fight.getText(), is("Fight"));
        clickOn("#action");

        verifyThat(fight.getText(), is("Next turn"));
    }

    @Test
    public void should_rewound_first_companion_in_the_second_turn() {

        initPersonages(Frodo, 0, null, 0, GoblinMarksman, 0, null,0);

        clickOn("#action"); // fight 1
        clickOn("#action"); // next turn
        clickOn("#action"); // fight 1

        verifyPersonage(companion1, true, true, false, false);

        verifyThat(companion1.getChildren().get(2), isVisible());
        verifyThat(info.getText(), endsWith("Frodo has been wounded.\nStart next turn\nFrodo has been wounded.\n"));
    }

    @Test
    public void should_kill_scout_after_two_wounds() {

        initPersonages(Frodo, 0, Gandalf, 0, GoblinMarksman, 0, MoriaScout,1);

        clickOn("#action"); // fight 1
        clickOn("#action"); // fight 2

        verifyThat(minion2, isInvisible());
        verifyThat(info.getText(), endsWith("Moria Scout has been killed.\n"));
    }

    @Test
    public void should_not_fight_when_no_opponent() {

        initPersonages(Frodo, 0, Gandalf, 0, GoblinMarksman, 0, null,0);

        clickOn("#action"); // fight 1

        verifyThat(fight.getText(), is("Next turn"));
    }

    @Test
    public void should_end_game_when_Frodo_dies() {

        initPersonages(Frodo, 3, null, 0, null, 0, null,0);
        CBoard cBoard = loader.getController();
        cBoard.addCompanion(Frodo, 3);
        cBoard.addMinion(GoblinMarksman);

        clickOn("#action"); // fight 1

        verifyThat(info.getText(), endsWith("Frodo has been killed. YOU LOOSE !!!!\n"));
        verifyThat(fight.isDisabled(), is(true));
    }

    private void initPersonages(Name companion1, int nbWoundC1, Name companion2, int nbWoundC2, Name minion1, int nbWoundM1, Name minion2, int nbWoundM2) {
        CBoard cBoard = loader.getController();
        if(companion1 != null) cBoard.addCompanion(companion1, nbWoundC1);
        if(companion2 != null) cBoard.addCompanion(companion2, nbWoundC2);
        if(minion1 != null) cBoard.addMinion(minion1, nbWoundM1);
        if(minion2 != null) cBoard.addMinion(minion2, nbWoundM2);
    }

    private void verifyPersonage(AnchorPane personage, boolean has1Wound, boolean has2Wounds, boolean has3Wounds, boolean has4Wounds) {
        verifyThat(personage.getChildren().get(0), is(instanceOf(AnchorPane.class)));
        verifyThat(personage.getChildren().get(1), has1Wound ? isVisible() : isInvisible());
        verifyThat(personage.getChildren().get(2), has2Wounds ? isVisible() : isInvisible());
        verifyThat(personage.getChildren().get(3), has3Wounds ? isVisible() : isInvisible());
        verifyThat(personage.getChildren().get(4), has4Wounds ? isVisible() : isInvisible());
    }
}