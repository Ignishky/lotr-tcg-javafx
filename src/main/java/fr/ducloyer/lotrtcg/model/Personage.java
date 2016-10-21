package fr.ducloyer.lotrtcg.model;

import lombok.Data;

@Data
public class Personage {

    private Card personage;
    private int nbWound;

    public String getName() {
        return personage.getName();
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
}
