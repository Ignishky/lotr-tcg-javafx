package fr.ducloyer.lotrtcg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    private int collection;
    private String name;
    private String picture;

    public void copy(Card aCard) {
        collection = aCard.collection;
        name = aCard.name;
        picture = aCard.picture;
    }
}
