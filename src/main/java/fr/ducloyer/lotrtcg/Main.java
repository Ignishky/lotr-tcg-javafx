package fr.ducloyer.lotrtcg;

import fr.ducloyer.lotrtcg.controller.CBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/board.fxml"));
        AnchorPane anchorPane = loader.load();
        CBoard cBoard = loader.getController();
        cBoard.addCompanion(1290);
        cBoard.addCompanion(1364);
        cBoard.addMinion(1176);
        cBoard.addMinion(1191);

        primaryStage.setTitle("LOTR-TCG");
        primaryStage.getIcons().add(new Image("/images/ring-icon-32.png"));
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();
    }
}
