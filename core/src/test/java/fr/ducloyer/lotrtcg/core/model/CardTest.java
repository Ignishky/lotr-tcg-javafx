package fr.ducloyer.lotrtcg.core.model;

import org.junit.Test;

import static fr.ducloyer.lotrtcg.core.model.Card.Side.FREE_PEOPLE;
import static fr.ducloyer.lotrtcg.core.model.CardLoader.loadCard;
import static fr.ducloyer.lotrtcg.core.model.Name.Gandalf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CardTest {

    @Test
    public void should_load_card_from_number() {

        Card card = loadCard(Gandalf);

        assertThat(card.getCollection(), equalTo(1364));
        assertThat(card.getSide(), equalTo(FREE_PEOPLE));
        assertThat(card.getName(), equalTo("Gandalf"));
        assertThat(card.getPicture(), equalTo("/card/Fellowship/LOTR-EN01364.jpg"));
        assertThat(card.getStrength(), equalTo(7));
        assertThat(card.getVitality(), equalTo(4));
    }
}