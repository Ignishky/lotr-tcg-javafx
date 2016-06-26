package fr.ducloyer.lotrtcg;

import fr.ducloyer.lotrtcg.controller.CardController;
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/card.fxml"));
        AnchorPane anchorPane = loader.load();
        loader.<CardController>getController().displayCard(1364);

        primaryStage.setTitle("LOTR-TCG");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/ring-icon-32.png")));
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();
    }
}
