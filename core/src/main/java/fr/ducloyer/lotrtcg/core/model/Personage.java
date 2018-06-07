package fr.ducloyer.lotrtcg.core.model;

import lombok.Data;

import static fr.ducloyer.lotrtcg.core.model.CardLoader.loadCard;

@Data
public class Personage {

    private Card personage;
    private int nbWound = 0;

    public Personage(Name name) {
        if (name != null) {
            personage = loadCard(name);
        }
    }

    public void addWound() {
        nbWound++;
    }

    public String getName() {
        return personage.getName();
    }

    public int getStrength() {
        return personage.getStrength();
    }

    public int getVitality() {
        return personage.getVitality();
    }
}
