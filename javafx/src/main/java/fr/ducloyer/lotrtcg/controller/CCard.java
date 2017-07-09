package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.MouseButton.SECONDARY;
import static javafx.stage.Modality.APPLICATION_MODAL;

@Slf4j
public class CCard extends Card implements Initializable {

    public static final double HEIGHT = 497.0;
    public static final double WIDTH = 357.0;

    public static final double MIN_HEIGHT = 150.0;
    public static final double MIN_WIDTH = 109.0;

    @FXML
    private ImageView card;
    @FXML
    private ImageView zoom;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {}

    public void addCard(int number) {
        super.copy(number);
        card.setImage(new Image(picture));
    }

    @FXML
    private void showBigDisplay(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton() == SECONDARY) {
            Stage stage;
            if (zoom == null || !zoom.isVisible()) {
                log.info("Zoom on {}", name);
                stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/zoom.fxml"));
                AnchorPane anchorPane = loader.load();
                ImageView zoom = (ImageView) anchorPane.getChildren().get(0);
                zoom.setImage(new Image(picture));
                stage.setScene(new Scene(anchorPane));
                stage.initModality(APPLICATION_MODAL);
                stage.initOwner(card.getScene().getWindow());
                stage.showAndWait();
            }
            else {
                stage = (Stage) zoom.getScene().getWindow();
                stage.close();
            }
        }
    }
}
