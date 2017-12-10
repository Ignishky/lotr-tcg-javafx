package fr.ducloyer.lotrtcg;

import fr.ducloyer.lotrtcg.controller.CBoard;
import fr.ducloyer.lotrtcg.scene.LocatedImage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static fr.ducloyer.lotrtcg.core.model.Name.*;

@Slf4j
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Start a new game.");

        prepareStage(primaryStage);
    }

    private AnchorPane loadBoard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/board.fxml"));
        AnchorPane anchorPane = loader.load();
        CBoard board = loader.getController();

        board.addCompanion(Frodo);
        board.addCompanion(Gandalf);
        board.addMinion(GoblinMarksman);
        board.addMinion(MoriaScout);

        return anchorPane;
    }

    private void prepareStage(Stage primaryStage) throws IOException {

        primaryStage.setTitle("LOTR-TCG");
        primaryStage.getIcons().add(new LocatedImage("/images/ring-icon-32.png"));
        primaryStage.setScene(new Scene(loadBoard()));
        primaryStage.show();
    }
}
