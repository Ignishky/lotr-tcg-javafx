package fr.ducloyer.lotrtcg.core.model;

import org.junit.Test;

import static fr.ducloyer.lotrtcg.core.model.Card.Name.Gandalf;
import static fr.ducloyer.lotrtcg.core.model.Card.Side.FREE_PEOPLE;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CardTest {

    @Test
    public void should_load_card_from_number() {

        Card card = new Card();
        card.load(Gandalf.getCollection());

        assertThat(card.getCollection(), is(1364));
        assertThat(card.getSide(), is(FREE_PEOPLE));
        assertThat(card.getName(), is("Gandalf"));
        assertThat(card.getPicture(), is("/card/Fellowship/LOTR-EN01364.jpg"));
        assertThat(card.getStrength(), is(7));
        assertThat(card.getVitality(), is(4));
    }
}