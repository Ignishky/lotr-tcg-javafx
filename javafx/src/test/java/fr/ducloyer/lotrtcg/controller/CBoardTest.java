package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Name;
import fr.ducloyer.lotrtcg.scene.LocatedImage;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssertContext;
import org.testfx.service.finder.NodeFinder;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.core.model.Name.Frodo;
import static fr.ducloyer.lotrtcg.core.model.Name.Gandalf;
import static fr.ducloyer.lotrtcg.core.model.Name.GoblinMarksman;
import static fr.ducloyer.lotrtcg.core.model.Name.MoriaScout;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CBoardTest extends AbstractControllerTest {

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

        companion1 = NODE_FINDER.lookup("#companion1").query();
        companion2 = NODE_FINDER.lookup("#companion2").query();
        minion1 = NODE_FINDER.lookup("#minion1").query();
        minion2 = NODE_FINDER.lookup("#minion2").query();
        fight = NODE_FINDER.lookup("#action").query();
        info = NODE_FINDER.lookup("#info").query();
    }

    @Test
    public void should_load_board() {

        initPersonages(Frodo, 0, null, GoblinMarksman, null, 0);

        verifyPersonage(companion1, 0);
        verifyThat(companion2, isInvisible());
        verifyPersonage(minion1, 0);
        verifyThat(minion2, isInvisible());

        verifyThat(fight, isVisible());
        verifyThat(fight.getLayoutX(), equalTo(100.0));
        verifyThat(fight.getLayoutY(), equalTo(328.0));

        verifyThat(info.getText(), equalTo("Play card Frodo.\nPlay card Goblin Marksman.\n"));
    }

    @Test
    public void should_wound_personage_for_the_fight() {

        initPersonages(Frodo, 0, Gandalf, GoblinMarksman, MoriaScout, 0);

        clickOn("#action");

        verifyPersonage(companion1, 1);
        verifyThat(info.getText(), endsWith("Frodo has been wounded.\n"));

        clickOn("#action");

        verifyPersonage(minion2, 1);
        verifyThat(info.getText(), endsWith("Moria Scout has been wounded.\n"));
    }

    @Test
    public void should_end_turn_when_all_companion_have_fight() {

        initPersonages(Frodo, 0, Gandalf, GoblinMarksman, MoriaScout, 0);

        verifyThat(fight.getText(), equalTo("Fight"));
        clickOn("#action");

        verifyThat(fight.getText(), equalTo("Fight"));
        clickOn("#action");

        verifyThat(fight.getText(), equalTo("Next turn"));
    }

    @Test
    public void should_rewound_first_companion_in_the_second_turn() {

        initPersonages(Frodo, 0, null, GoblinMarksman, null, 0);

        clickOn("#action"); // fight 1
        clickOn("#action"); // next turn
        clickOn("#action"); // fight 1

        verifyPersonage(companion1, 2);

        verifyThat(info.getText(), endsWith("Frodo has been wounded.\nStart next turn\nFrodo has been wounded.\n"));
    }

    @Test
    public void should_kill_scout_after_two_wounds() {

        initPersonages(Frodo, 0, Gandalf, GoblinMarksman, MoriaScout, 1);

        clickOn("#action"); // fight 1
        clickOn("#action"); // fight 2

        verifyThat(minion2, isInvisible());
        verifyThat(info.getText(), endsWith("Moria Scout has been killed.\n"));
    }

    @Test
    public void should_not_fight_when_no_opponent() {

        initPersonages(Frodo, 0, Gandalf, GoblinMarksman, null, 0);

        clickOn("#action"); // fight 1

        verifyThat(fight.getText(), equalTo("Next turn"));
    }

    @Test
    public void should_end_game_when_Frodo_dies() {

        initPersonages(Frodo, 3, null, null, null, 0);
        CBoard cBoard = loader.getController();
        cBoard.addCompanion(Frodo, 3);
        cBoard.addMinion(GoblinMarksman);

        clickOn("#action"); // fight 1

        verifyThat(info.getText(), endsWith("Frodo has been killed. YOU LOOSE !!!!\n"));
        verifyThat(fight.isDisabled(), equalTo(true));
    }

    private void initPersonages(Name companion1, int nbWoundC1, Name companion2, Name minion1, Name minion2, int nbWoundM2) {
        CBoard cBoard = loader.getController();
        if (companion1 != null) cBoard.addCompanion(companion1, nbWoundC1);
        if (companion2 != null) cBoard.addCompanion(companion2, 0);
        if (minion1 != null) cBoard.addMinion(minion1, 0);
        if (minion2 != null) cBoard.addMinion(minion2, nbWoundM2);
    }

    private void verifyPersonage(AnchorPane personage, int nbWounds) {
        verifyThat(personage, isVisible());

        ObservableList<Node> wounds = NODE_FINDER.lookup("#wounds").<TilePane> query().getChildren();
        verifyThat(wounds.size(), equalTo(nbWounds));
        for (Node wound : wounds) {
            verifyThat(((LocatedImage) ((ImageView) wound).getImage()).getUrl(), equalTo("/images/wound.png"));
        }
    }
}