package fr.ducloyer.lotrtcg.core.utils;

import fr.ducloyer.lotrtcg.core.model.Personage;
import fr.ducloyer.lotrtcg.core.utils.FightResult.Actions;

import static fr.ducloyer.lotrtcg.core.utils.FightResult.Actions.KILL;
import static fr.ducloyer.lotrtcg.core.utils.FightResult.Actions.WOUND;

public class FightResolver {

    public static FightResult fight(Personage companion, Personage minion) {
        Personage wounded = companion.getStrength() > minion.getStrength() ? minion : companion;
        Actions action = wounded.getNbWound() + 1 < wounded.getVitality() ? WOUND : KILL;
        return new FightResult(action, wounded);
    }
}
