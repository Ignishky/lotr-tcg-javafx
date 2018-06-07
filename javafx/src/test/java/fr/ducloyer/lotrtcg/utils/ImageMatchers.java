package fr.ducloyer.lotrtcg.utils;

import fr.ducloyer.lotrtcg.scene.LocatedImage;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ImageMatchers {

    public static Matcher<LocatedImage> hasImage(final String s) {
        return new BaseMatcher<LocatedImage>() {
            @Override
            public boolean matches(Object item) {
                LocatedImage image = (LocatedImage) item;
                return imageAreEquals(new LocatedImage(s), image);
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue(s);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText("was ").appendValue(((LocatedImage) item).getUrl());
            }
        };
    }

    private static boolean imageAreEquals(LocatedImage image1, LocatedImage image2) {
        for (int i = 0; i < image1.getWidth(); i++) {
            for (int j = 0; j < image1.getHeight(); j++) {
                if (!image1.getPixelReader().getColor(i, j).equals(image2.getPixelReader().getColor(i, j))) return false;
            }
        }
        return true;
    }
}
