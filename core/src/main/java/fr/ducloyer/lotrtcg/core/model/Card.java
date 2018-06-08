package fr.ducloyer.lotrtcg.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Card {

    public enum Side {
        FREE_PEOPLE, SHADOW
    }

    private final int collection;
    private final Side side;
    private final String name;
    private final String picture;
    private final int strength;
    private final int vitality;
}
