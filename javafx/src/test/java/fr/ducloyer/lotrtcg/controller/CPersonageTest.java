package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Card;
import fr.ducloyer.lotrtcg.scene.LocatedImage;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssertContext;
import org.testfx.service.finder.NodeFinder;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.controller.CCard.MIN_HEIGHT;
import static fr.ducloyer.lotrtcg.controller.CCard.MIN_WIDTH;
import static fr.ducloyer.lotrtcg.core.model.Card.Side.FREE_PEOPLE;
import static fr.ducloyer.lotrtcg.core.model.Name.Gandalf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class CPersonageTest extends AbstractControllerTest {

    private static final NodeFinder NODE_FINDER = new FxAssertContext().getNodeFinder();
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
        verifyThat("#wounds", hasChildren(0, ""));
    }

    @Test
    public void should_init_personage() {

        CPersonage controller = loader.getController();
        controller.addPersonage(Gandalf);

        verifyThat(controller.getAbstraction().getNbWound(), equalTo(0));
        verifyThat(controller.getAbstraction().getPersonage(), equalTo(new Card(Gandalf.getCollection(), FREE_PEOPLE, "Gandalf", "/card/Fellowship/LOTR-EN01364.jpg", 7, 4)));
        verifyThat(((TilePane) NODE_FINDER.lookup("#wounds").query()).getChildren().size(), equalTo(0));
    }

    @Test
    public void should_add_wound() throws InterruptedException {
        CPersonage controller = loader.getController();
        controller.addPersonage(Gandalf);
        controller.addWound();
        controller.addWound();
        controller.addWound();

        ObservableList<Node> wounds = NODE_FINDER.lookup("#wounds").<TilePane>query().getChildren();
        // Wait for all runLater calls to end
        Thread.sleep(500);
        verifyThat(wounds.size(), equalTo(3));
        verifyThat(((LocatedImage) ((ImageView) wounds.get(0)).getImage()).getUrl(), equalTo("/images/wound.png"));
        verifyThat(((LocatedImage) ((ImageView) wounds.get(1)).getImage()).getUrl(), equalTo("/images/wound.png"));
        verifyThat(((LocatedImage) ((ImageView) wounds.get(2)).getImage()).getUrl(), equalTo("/images/wound.png"));
    }
}