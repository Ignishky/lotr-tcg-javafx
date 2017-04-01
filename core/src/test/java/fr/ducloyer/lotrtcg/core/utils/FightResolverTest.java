package fr.ducloyer.lotrtcg.core.utils;

import fr.ducloyer.lotrtcg.core.model.Personage;
import org.junit.Test;

import static fr.ducloyer.lotrtcg.core.model.Card.Name.*;
import static fr.ducloyer.lotrtcg.core.utils.FightResolver.fight;
import static fr.ducloyer.lotrtcg.core.utils.FightResult.Actions.KILL;
import static fr.ducloyer.lotrtcg.core.utils.FightResult.Actions.WOUND;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FightResolverTest {

    @Test
    public void minion_should_win_when_his_strength_is_greater_than_companion_one() {
        Personage companion = new Personage(Frodo);
        Personage minion = new Personage(GoblinMarksman);

        FightResult fightResult = fight(companion, minion);

        assertThat(fightResult, equalTo(new FightResult(WOUND, companion)));
    }

    @Test
    public void minion_should_win_when_his_strength_is_equal_to_companion_one() {
        Personage companion = new Personage(Gandalf);
        Personage minion = new Personage(GoblinMarksman);

        FightResult fightResult = fight(companion, minion);

        assertThat(fightResult, equalTo(new FightResult(WOUND, companion)));
    }

    @Test
    public void companion_should_win_when_his_strength_is_greater_than_minion_one() {
        Personage companion = new Personage(Gandalf);
        Personage minion = new Personage(MoriaScout);

        FightResult fightResult = fight(companion, minion);

        assertThat(fightResult, equalTo(new FightResult(WOUND, minion)));
    }

    @Test
    public void should_kill_loser_when_wounds_equals_vitality() {
        Personage companion = new Personage(Gandalf);
        Personage minion = new Personage(MoriaScout);
        minion.addWound();

        FightResult fightResult = fight(companion, minion);

        assertThat(fightResult, equalTo(new FightResult(KILL, minion)));
    }
}