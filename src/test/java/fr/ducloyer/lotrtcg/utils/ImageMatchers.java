package fr.ducloyer.lotrtcg.utils;

import javafx.scene.image.Image;
import org.hamcrest.Matcher;

import static org.testfx.matcher.base.GeneralMatchers.baseMatcher;

public class ImageMatchers {

    public static Matcher<Image> hasImage(String s) {
        return baseMatcher("Images are differents", image -> imageAreEquals(image, new Image(s)));
    }

    private static boolean imageAreEquals(Image image1, Image image2) {
        for (int i = 0; i < image1.getWidth(); i++)
        {
            for (int j = 0; j < image1.getHeight(); j++)
            {
                if (!image1.getPixelReader().getColor(i, j).equals(image2.getPixelReader().getColor(i, j))) return false;
            }
        }
        return true;
    }
}
