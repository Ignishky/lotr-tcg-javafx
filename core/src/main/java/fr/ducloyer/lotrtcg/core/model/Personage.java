package fr.ducloyer.lotrtcg.core.model;

import lombok.Data;

@Data
public class Personage {

    private Card personage;
    protected int nbWound = 0;

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
