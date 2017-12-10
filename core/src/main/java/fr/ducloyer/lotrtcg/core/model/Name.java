package fr.ducloyer.lotrtcg.core.model;

import lombok.Getter;

@Getter
public enum Name {
    Back(0),

    GoblinMarksman(1176),
    MoriaScout(1191),
    Frodo(1290),
    Gandalf(1364);

    private final int collection;

    Name(int collection) {
        this.collection = collection;
    }
}

