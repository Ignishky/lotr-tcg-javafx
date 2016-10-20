package fr.ducloyer.lotrtcg;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static fr.ducloyer.lotrtcg.utils.ImageMatchers.hasImage;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.testfx.api.FxAssert.verifyThat;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new Main().start(stage);
    }

    @Test
    public void should_test_window_init() {
        Stage registeredStage = FxToolkit.toolkitContext().getRegisteredStage();
        verifyThat(registeredStage.getTitle(), equalTo("LOTR-TCG"));
        verifyThat(registeredStage.getIcons(), hasSize(1));
        verifyThat(registeredStage.getIcons().get(0), hasImage("/images/ring-icon-32.png"));
    }
}