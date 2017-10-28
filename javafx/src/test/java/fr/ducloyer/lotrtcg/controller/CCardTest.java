package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.scene.LocatedImage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssertContext;
import org.testfx.service.finder.NodeFinder;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.controller.CCard.*;
import static fr.ducloyer.lotrtcg.core.model.Card.Name.Gandalf;
import static fr.ducloyer.lotrtcg.utils.ImageMatchers.hasImage;
import static javafx.scene.input.MouseButton.SECONDARY;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CCardTest extends AbstractControllerTest {

    private static final NodeFinder NODE_FINDER = new FxAssertContext().getNodeFinder();
    private final FXMLLoader loader = new FXMLLoader();

    @Override
    public void start(Stage stage) throws IOException {
        loader.setLocation(getClass().getResource("/view/card.fxml"));
        Parent anchorPane = loader.load();
        stage.setScene(new Scene(anchorPane, MIN_WIDTH, MIN_HEIGHT));
        stage.show();
    }

    @Test
    public void should_load_card () {
        loader.<CCard>getController().addCard(Gandalf.getCollection());

        ImageView card = new FxAssertContext().getNodeFinder().lookup("#card").query();

        verifyThat(card, isVisible());
        verifyThat(card.getFitWidth(), equalTo(MIN_WIDTH));
        verifyThat(card.getFitHeight(), equalTo(MIN_HEIGHT));
        verifyThat((LocatedImage) card.getImage(), hasImage("/card/Fellowship/LOTR-EN01364.jpg"));
    }

    @Test
    public void should_zoom_on_card_with_right_click() {
        loader.<CCard>getController().addCard(Gandalf.getCollection());
        ImageView card = NODE_FINDER.lookup("#card").query();
        clickOn(card, SECONDARY);

        ImageView zoom = NODE_FINDER.lookup("#zoom").query();
        verifyThat(zoom, isVisible());
        verifyThat(zoom.getFitWidth(), equalTo(WIDTH));
        verifyThat(zoom.getFitHeight(), equalTo(HEIGHT));
        verifyThat((LocatedImage) zoom.getImage(), hasImage("/card/Fellowship/LOTR-EN01364.jpg"));
        clickOn(zoom, SECONDARY);

        verifyThat(zoom.getScene().getWindow().isShowing(), equalTo(false));
    }
}
