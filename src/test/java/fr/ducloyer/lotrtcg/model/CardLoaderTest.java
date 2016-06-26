package fr.ducloyer.lotrtcg.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardLoaderTest {

    private final CardLoader cardLoader = CardLoader.getInstance();

    @Test
    public void should_load_back_with_0() throws Exception {
        Card card = cardLoader.loadCard(0);

        assertThat(card).isEqualTo(new Card(0, "/card/Back_V.jpg"));
    }

    @Test
    public void should_load_card_with_given_number() throws Exception {
        Card card = cardLoader.loadCard(1364);

        assertThat(card).isEqualTo(new Card(1364, "/card/Fellowship/LOTR-EN01364.jpg"));
    }
}