package fr.ducloyer.lotrtcg.core.model;

import org.junit.Test;

import static fr.ducloyer.lotrtcg.core.model.Card.Name.Gandalf;
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
        Card card = cardLoader.loadCard(Gandalf.getCollection());

        assertThat(card, equalTo(new Card(Gandalf.getCollection(), "Gandalf", "/card/Fellowship/LOTR-EN01364.jpg", 7)));
    }
}