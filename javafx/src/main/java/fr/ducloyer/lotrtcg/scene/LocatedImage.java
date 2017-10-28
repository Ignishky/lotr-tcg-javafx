package fr.ducloyer.lotrtcg.scene;

import javafx.scene.image.Image;

public class LocatedImage extends Image{

    private final String url;

    public LocatedImage(String url) {
        super(url);
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
