package fr.ducloyer.lotrtcg;

import fr.ducloyer.lotrtcg.controller.CBoard;
import fr.ducloyer.lotrtcg.controller.CCard;
import fr.ducloyer.lotrtcg.controller.CPersonage;
import fr.ducloyer.lotrtcg.scene.LocatedImage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.core.model.Card.Name.*;

@Slf4j
public class MainRun extends Application {

    private static String mode = "all";

    public static void main(String[] args) {
        mode = args != null && args.length > 0 ? args[0] : "all";

        log.info("Start a new game in mode '{}'.", mode);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent parent;
        switch (mode) {
            case "card":
                parent = testCard();
                break;
            case "personage":
                parent = testPersonage();
                break;
            case "board":
                parent = testBoard();
                break;
            default:
                parent = testBoard();
        }

        prepareStage(primaryStage, parent);
    }

    private Parent testCard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/card.fxml"));
        ImageView imageView = loader.load();
        CCard card = loader.getController();

        card.addCard(Frodo);

        return new VBox(imageView);
    }

    private Parent testPersonage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/personage.fxml"));
        AnchorPane anchorPane = loader.load();
        CPersonage personage = loader.getController();

        personage.addPersonage(Gandalf, 2);

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

    private void prepareStage(Stage primaryStage, Parent parent) {
        primaryStage.setTitle("LOTR-TCG");
        primaryStage.getIcons().add(new LocatedImage("/images/ring-icon-32.png"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }
}
