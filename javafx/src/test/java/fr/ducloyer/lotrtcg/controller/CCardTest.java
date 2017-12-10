package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.scene.LocatedImage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssertContext;
import org.testfx.service.finder.NodeFinder;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.controller.CCard.*;
import static fr.ducloyer.lotrtcg.core.model.Name.Gandalf;
import static fr.ducloyer.lotrtcg.utils.ImageMatchers.hasImage;
import static javafx.scene.input.MouseButton.SECONDARY;
import static org.hamcrest.Matchers.is;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class CCardTest extends AbstractControllerTest {

    private static final NodeFinder NODE_FINDER = new FxAssertContext().getNodeFinder();
    private final FXMLLoader loader = new FXMLLoader();

    @Override
    public void start(Stage stage) throws IOException {
        loader.setLocation(getClass().getResource("/view/card.fxml"));
        ImageView imageView = loader.load();
        stage.setScene(new Scene(new VBox(imageView), MIN_WIDTH, MIN_HEIGHT));
        stage.show();
    }

    @Test
    public void should_load_card () {
        loader.<CCard>getController().addCard(Gandalf);

        ImageView card = new FxAssertContext().getNodeFinder().lookup("#card").query();

        verifyThat(card, isVisible());
        verifyThat(card.getFitWidth(), is(MIN_WIDTH));
        verifyThat(card.getFitHeight(), is(MIN_HEIGHT));
        verifyThat((LocatedImage) card.getImage(), hasImage("/card/Fellowship/LOTR-EN01364.jpg"));
    }

    @Test
    public void should_zoom_on_card_with_right_click() {
        loader.<CCard>getController().addCard(Gandalf);
        ImageView card = NODE_FINDER.lookup("#card").query();

        clickOn(card, SECONDARY);

        ImageView zoom = NODE_FINDER.lookup("#zoom").query();
        verifyThat(zoom, isVisible());
        verifyThat(zoom.getFitWidth(), is(WIDTH));
        verifyThat(zoom.getFitHeight(), is(HEIGHT));
        verifyThat((LocatedImage) zoom.getImage(), hasImage("/card/Fellowship/LOTR-EN01364.jpg"));

        Stage stage = (Stage) zoom.getScene().getWindow();
        verifyThat(stage.isShowing(), is(true));
        verifyThat(stage.getTitle(), is("Zoom on Gandalf"));
        verifyThat((LocatedImage) stage.getIcons().get(0), hasImage("/images/ring-icon-32.png"));

        clickOn(zoom, SECONDARY);

        verifyThat(stage.isShowing(), is(false));
    }
}
