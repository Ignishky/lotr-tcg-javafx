package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static javafx.scene.input.MouseButton.SECONDARY;
import static javafx.stage.StageStyle.UTILITY;

@Slf4j
public class CCard extends Card implements Initializable {

    public static final int HEIGHT = 150;
    public static final int WIDTH = 109;

    @FXML
    private ImageView card;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        addCard(0);
    }

    public void addCard(int number) {
        super.copy(number);
        card.setImage(new Image(picture));
    }

    public void showBigDisplay(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == SECONDARY) {
            Alert alert = new Alert(INFORMATION);
            alert.initStyle(UTILITY);
            alert.setTitle("Zoom on card");
            alert.setHeaderText(null);
            alert.setGraphic(new ImageView(new Image(picture)));

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.setMaxHeight(550);
            stage.setMaxWidth(380);
            stage.getIcons().add(new Image("/images/ring-icon-32.png"));

            alert.show();
        }
    }
}
