package fr.ducloyer.lotrtcg.core.utils;

import fr.ducloyer.lotrtcg.core.model.Personage;
import lombok.Data;

@Data
public class FightResult {

    public enum Actions {
        WOUND, KILL
    }

    private final Actions action;
    private final Personage personage;
}
