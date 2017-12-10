package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Card;
import fr.ducloyer.lotrtcg.core.model.Name;
import fr.ducloyer.lotrtcg.scene.LocatedImage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static fr.ducloyer.lotrtcg.core.model.CardLoader.loadCard;
import static javafx.scene.input.MouseButton.SECONDARY;
import static javafx.stage.Modality.APPLICATION_MODAL;

@Slf4j
public class CCard implements Initializable {

    public static final double HEIGHT = 497.0;
    public static final double WIDTH = 357.0;

    public static final double MIN_HEIGHT = 150.0;
    public static final double MIN_WIDTH = 109.0;

    private Card abstraction;

    @FXML
    private ImageView card;
    @FXML
    private ImageView zoom;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {}

    public String getName() {
        return abstraction.getName();
    }

    public void addCard(Name name) {
        abstraction = loadCard(name);
        card.setImage(new LocatedImage(abstraction.getPicture()));
    }

    @FXML
    private void showBigDisplay(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton() == SECONDARY) {
            Stage stage;
            if (zoom == null || !zoom.isVisible()) {
                log.info("Zoom on {}", abstraction.getName());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/zoom.fxml"));
                AnchorPane anchorPane = loader.load();
                ImageView zoom = (ImageView) anchorPane.getChildren().get(0);
                zoom.setImage(new LocatedImage(abstraction.getPicture()));
                stage = new Stage();
                stage.setTitle("Zoom on " + abstraction.getName());
                stage.getIcons().add(new LocatedImage("/images/ring-icon-32.png"));
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
