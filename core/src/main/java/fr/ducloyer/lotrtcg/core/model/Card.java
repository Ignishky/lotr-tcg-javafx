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

    protected int collection;
    protected Side side;
    protected String name;
    protected String picture;
    protected int strength;
    protected int vitality;
}
