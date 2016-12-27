package fr.ducloyer.lotrtcg.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    @Getter
    public enum Name {
        GoblinMarksman(1176),
        MoriaScaout(1191),
        Frodo(1290),
        Gandalf(1364);

        private final int collection;

        Name(int collection) {
            this.collection = collection;
        }
    }

    private static final CardLoader LOADER = CardLoader.getInstance();

    protected int collection;
    protected String name;
    protected String picture;
    protected int strength;

    public void copy(int number) {
        Card card = LOADER.loadCard(number);
        collection = card.collection;
        name = card.name;
        picture = card.picture;
        strength = card.strength;
    }
}
