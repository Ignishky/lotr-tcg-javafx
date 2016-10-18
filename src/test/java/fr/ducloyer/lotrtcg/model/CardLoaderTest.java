package fr.ducloyer.lotrtcg.model;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.testfx.api.FxAssert.verifyThat;

public class CardLoaderTest {

    private final CardLoader cardLoader = CardLoader.getInstance();

    @Test
    public void should_load_back_with_0() throws Exception {
        Card card = cardLoader.loadCard(0);

        verifyThat(card, equalTo(new Card(0, null, "/card/Back_V.jpg")));
    }

    @Test
    public void should_load_card_with_given_number() throws Exception {
        Card card = cardLoader.loadCard(1364);

        verifyThat(card, equalTo(new Card(1364, "Gandalf", "/card/Fellowship/LOTR-EN01364.jpg")));
    }
}