package fr.ducloyer.lotrtcg.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;

import static com.google.common.base.Throwables.propagate;
import static org.assertj.core.api.Assertions.assertThat;

public class CCardTest extends GuiTest {

    private FXMLLoader loader;

    @Override
    protected Parent getRootNode() {
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/card.fxml"));
            return loader.<AnchorPane>load();
        } catch (IOException ex) {
            throw propagate(ex);
        }
    }

    @Test
    public void should_load_Back_card() {
        ImageView card = find("#card");
        assertThat(card.getImage().getWidth()).isEqualTo(357.0);
        assertThat(card.getImage().getHeight()).isEqualTo(500.0);
    }

    @Test
    public void should_load_card () {
        loader.<CCard>getController().addCard(1364);

        ImageView card = find("#card");

        assertThat(card.getImage().getWidth()).isEqualTo(357.0);
        assertThat(card.getImage().getHeight()).isEqualTo(497.0);
    }
}
