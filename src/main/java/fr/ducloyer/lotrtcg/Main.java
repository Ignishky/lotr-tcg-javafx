package fr.ducloyer.lotrtcg;

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
        Image icon = new Image(getClass().getResourceAsStream("/images/ring-icon-32.png"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/board.fxml"));
        AnchorPane anchorPane = loader.load();

        primaryStage.setTitle("LOTR-TCG");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();
    }
}
