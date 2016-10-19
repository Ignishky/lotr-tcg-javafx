package fr.ducloyer.lotrtcg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    private static final CardLoader LOADER = CardLoader.getInstance();

    protected int collection;
    protected String name;
    protected String picture;

    public void copy(int number) {
        Card card = LOADER.loadCard(number);
        collection = card.collection;
        name = card.name;
        picture = card.picture;
    }
}
