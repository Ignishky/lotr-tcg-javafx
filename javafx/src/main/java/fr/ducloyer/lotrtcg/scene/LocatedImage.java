package fr.ducloyer.lotrtcg.scene;

import javafx.scene.image.Image;
import lombok.Getter;

public class LocatedImage extends Image {

    @Getter
    private final String url;

    public LocatedImage(String url) {
        super(url);
        this.url = url;
    }
}
