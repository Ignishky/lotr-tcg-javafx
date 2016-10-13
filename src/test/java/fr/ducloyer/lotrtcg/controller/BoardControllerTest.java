package fr.ducloyer.lotrtcg.controller;

import static com.google.common.base.Throwables.propagate;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
        AnchorPane companion1 = find("#companion1");
        assertThat(companion1.getChildren().get(0)).isInstanceOf(ImageView.class);

        AnchorPane companion2 = find("#companion2");
        assertThat(companion2.getChildren().get(0)).isInstanceOf(ImageView.class);

        AnchorPane minion1 = find("#minion1");
        assertThat(minion1.getChildren().get(0)).isInstanceOf(ImageView.class);

        AnchorPane minion2 = find("#minion2");
        assertThat(minion2.getChildren().get(0)).isInstanceOf(ImageView.class);

        Button fight = find("#fight");
        assertThat(fight.getLayoutX()).isEqualTo(92.0);
        assertThat(fight.getLayoutY()).isEqualTo(338.0);

        assertThat(companion1.getLayoutY()).isEqualTo(companion2.getLayoutY());
        assertThat(companion1.getLayoutX()).isEqualTo(minion1.getLayoutX());
        assertThat(minion1.getLayoutY()).isEqualTo(minion2.getLayoutY());
        assertThat(companion2.getLayoutX()).isEqualTo(minion2.getLayoutX());
    }

    @Test
    public void should_wound_companion_for_the_fight() {
        Button fight = find("#fight");
        click(fight);

        AnchorPane companion1 = find("#companion1");
        assertThat(companion1.getChildren().get(1).isVisible()).isTrue();
    }
}