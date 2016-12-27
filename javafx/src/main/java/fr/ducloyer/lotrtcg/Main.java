package fr.ducloyer.lotrtcg;

import fr.ducloyer.lotrtcg.controller.CBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import static fr.ducloyer.lotrtcg.core.model.Card.Name.*;

@Slf4j
public class Main extends Application {

    public static void main(String[] args) {
        log.info("Start a new game.");

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/board.fxml"));
        AnchorPane anchorPane = loader.load();
        CBoard cBoard = loader.getController();
        cBoard.addCompanion(Frodo);
        cBoard.addCompanion(Gandalf);
        cBoard.addMinion(GoblinMarksman);
        cBoard.addMinion(MoriaScout);

        primaryStage.setTitle("LOTR-TCG");
        primaryStage.getIcons().add(new Image("/images/ring-icon-32.png"));
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();
    }
}
