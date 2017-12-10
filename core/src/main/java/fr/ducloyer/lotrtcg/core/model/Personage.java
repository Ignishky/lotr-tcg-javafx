package fr.ducloyer.lotrtcg.core.model;

import lombok.Data;

@Data
public class Personage {

    private Card personage;
    protected int nbWound = 0;

    public Personage(Name card) {
        if (card != null) {
            personage = CardLoader.getInstance().loadCard(card.getCollection());
        }
    }

    public void addPersonage(Card personage) {
        this.personage = personage;
    }

    public void addWound() {
        nbWound++;
    }

    public int getStrength() {
        return personage.getStrength();
    }

    public int getVitality() {
        return personage.getVitality();
    }
}
