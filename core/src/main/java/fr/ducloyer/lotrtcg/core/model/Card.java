package fr.ducloyer.lotrtcg.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    public enum Side {
        FREE_PEOPLE, SHADOW
    }

    private static final CardLoader LOADER = CardLoader.getInstance();

    protected int collection;
    protected Side side;
    protected String name;
    protected String picture;
    protected int strength;
    protected int vitality;

    protected void load(int number) {
        Card card = LOADER.loadCard(number);
        collection = card.collection;
        side = card.side;
        name = card.name;
        picture = card.picture;
        strength = card.strength;
        vitality = card.vitality;
    }
}
