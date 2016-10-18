package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.model.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesVisibleException;

import java.io.IOException;

import static com.google.common.base.Throwables.propagate;
import static org.assertj.core.api.Assertions.assertThat;

public class CPersonageTest extends GuiTest {

    private FXMLLoader loader;

    @Override
    protected Parent getRootNode() {
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/personage.fxml"));
            return loader.<AnchorPane>load();
        } catch (IOException ex) {
            throw propagate(ex);
        }
    }

    @Test(expected = NoNodesVisibleException.class)
    public void should_not_display_wound_after_init() {
        find("#oneWound");
    }

    @Test
    public void should_init_personage() {
        CPersonage controller = loader.getController();
        controller.addPersonage(1364);

        assertThat(controller.getName()).isEqualTo(controller.getPersonage().getName());
        assertThat(controller.getNbWound()).isEqualTo(0);
        assertThat(controller.getPersonage()).isEqualTo(new Card(1364, "Gandalf", "/card/Fellowship/LOTR-EN01364.jpg"));
    }

    @Test
    public void should_add_wound() {
        CPersonage controller = loader.getController();
        controller.addPersonage(1364);
        controller.addWound();

        Label label = find("#oneWound");
        assertThat(label.isVisible()).isTrue();
    }
}