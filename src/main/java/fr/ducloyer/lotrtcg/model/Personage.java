package fr.ducloyer.lotrtcg.model;

import lombok.Data;

@Data
public class Personage {

    private Card personage;
    private int nbWound;

    public Personage(Card personage) {
        this.personage = personage;
        nbWound = 0;
    }

    public String getName() {
        return personage.getName();
    }

    public void addWound() {
        nbWound++;
    }
}
