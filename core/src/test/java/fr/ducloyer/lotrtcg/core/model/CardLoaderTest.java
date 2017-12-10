package fr.ducloyer.lotrtcg.core.model;

import org.junit.Test;

import static fr.ducloyer.lotrtcg.core.model.Name.Gandalf;
import static fr.ducloyer.lotrtcg.core.model.Card.Side.FREE_PEOPLE;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CardLoaderTest {

    private final CardLoader cardLoader = CardLoader.getInstance();

    @Test
    public void should_load_back_with_0() {
        Card card = cardLoader.loadCard(0);

        assertThat(card, is(new Card(0, null, null, "/card/Back_V.jpg", 0, 0)));
    }

    @Test
    public void should_load_card_with_given_number() {
        Card card = cardLoader.loadCard(Gandalf.getCollection());

        assertThat(card, is(new Card(Gandalf.getCollection(), FREE_PEOPLE, "Gandalf", "/card/Fellowship/LOTR-EN01364.jpg", 7, 4)));
    }
}