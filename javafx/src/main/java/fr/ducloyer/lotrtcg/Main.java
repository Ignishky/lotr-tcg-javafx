package fr.ducloyer.lotrtcg;

import fr.ducloyer.lotrtcg.controller.CBoard;
import fr.ducloyer.lotrtcg.controller.CCard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.core.model.Card.Name.*;

@Slf4j
public class Main extends Application {

    private static String mode = "all";

    public static void main(String[] args) {
        mode = args != null && args.length > 0 ? args[0] : "all";

        log.info("Start a new game in mode '{}'.", mode);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane;
        switch (mode) {
            case "card":
                anchorPane = testCard();
                break;
            case "board":
                anchorPane = testBoard();
                break;
            default:
                anchorPane = testBoard();
        }

        prepareStage(primaryStage, anchorPane);
    }

    private AnchorPane testCard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/card.fxml"));
        AnchorPane anchorPane = loader.load();
        CCard card = loader.getController();

        card.addCard(Frodo.getCollection());

        return anchorPane;
    }

    private AnchorPane testBoard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/board.fxml"));
        AnchorPane anchorPane = loader.load();
        CBoard board = loader.getController();

        board.addCompanion(Frodo);
        board.addCompanion(Gandalf);
        board.addMinion(GoblinMarksman);
        board.addMinion(MoriaScout);

        return anchorPane;
    }

    private void prepareStage(Stage primaryStage, AnchorPane anchorPane) {
        primaryStage.setTitle("LOTR-TCG");
        primaryStage.getIcons().add(new Image("/images/ring-icon-32.png"));
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();
    }
}
