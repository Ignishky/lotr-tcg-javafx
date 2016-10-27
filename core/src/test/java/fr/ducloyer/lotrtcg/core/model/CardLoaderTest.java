package fr.ducloyer.lotrtcg.core.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CardLoaderTest {

    private final CardLoader cardLoader = CardLoader.getInstance();

    @Test
    public void should_load_back_with_0() throws Exception {
        Card card = cardLoader.loadCard(0);

        assertThat(card, equalTo(new Card(0, null, "/card/Back_V.jpg", 0)));
    }

    @Test
    public void should_load_card_with_given_number() throws Exception {
        Card card = cardLoader.loadCard(1364);

        assertThat(card, equalTo(new Card(1364, "Gandalf", "/card/Fellowship/LOTR-EN01364.jpg", 7)));
    }
}