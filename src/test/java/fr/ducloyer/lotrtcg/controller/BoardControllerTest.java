package fr.ducloyer.lotrtcg.controller;

import static com.google.common.base.Throwables.propagate;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class BoardControllerTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/board.fxml"));
            return loader.<AnchorPane> load();
        }
        catch (IOException ex) {
            throw propagate(ex);
        }
    }

    @Test
    public void should_load_board() {
        ImageView companion1 = find("#companion1");
        assertThat(companion1.getFitWidth()).isEqualTo(108.2);
        assertThat(companion1.getFitHeight()).isEqualTo(150.6);
        assertThat(companion1.getRotate()).isEqualTo(0.0);

        ImageView companion2 = find("#companion2");
        assertThat(companion2.getFitWidth()).isEqualTo(108.2);
        assertThat(companion2.getFitHeight()).isEqualTo(150.6);
        assertThat(companion2.getRotate()).isEqualTo(0.0);

        ImageView minion1 = find("#minion1");
        assertThat(minion1.getFitWidth()).isEqualTo(108.2);
        assertThat(minion1.getFitHeight()).isEqualTo(150.6);
        assertThat(minion1.getRotate()).isEqualTo(0.0);

        ImageView minion2 = find("#minion2");
        assertThat(minion2.getFitWidth()).isEqualTo(108.2);
        assertThat(minion2.getFitHeight()).isEqualTo(150.6);
        assertThat(minion2.getRotate()).isEqualTo(0.0);

        assertThat(companion1.getLayoutY()).isEqualTo(companion2.getLayoutY());
        assertThat(companion1.getLayoutX()).isEqualTo(minion1.getLayoutX());
        assertThat(minion1.getLayoutY()).isEqualTo(minion2.getLayoutY());
        assertThat(companion2.getLayoutX()).isEqualTo(minion2.getLayoutX());
    }
}