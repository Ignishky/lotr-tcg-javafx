package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Card;
import fr.ducloyer.lotrtcg.core.utils.EndGameException;
import fr.ducloyer.lotrtcg.core.utils.FightResult;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.google.common.collect.Lists.newArrayList;
import static fr.ducloyer.lotrtcg.core.model.Card.Side.FREE_PEOPLE;
import static fr.ducloyer.lotrtcg.core.utils.FightResolver.fight;

@Slf4j
public class CBoard implements Initializable {

    @FXML
    private CPersonage companion1Controller;
    @FXML
    private CPersonage companion2Controller;

    private List<CPersonage> companions;
    private int nbCompanions = 0;

    @FXML
    private CPersonage minion1Controller;
    @FXML
    private CPersonage minion2Controller;

    private List<CPersonage> minions;
    private int nbMinions = 0;

    @FXML
    private Button action;
    private int fightPos = 0;

    @FXML
    private TextArea info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Toastr.init(info);
        companions = newArrayList(companion1Controller, companion2Controller);
        minions = newArrayList(minion1Controller, minion2Controller);
    }

    public void addCompanion(Card.Name card) {
        log.info("Add companion {}", card.getCollection());
        companions.get(nbCompanions).addPersonage(card.getCollection());
        nbCompanions++;
    }

    public void addMinion(Card.Name card) {
        log.info("Add minion {}", card.getCollection());
        minions.get(nbMinions).addPersonage(card.getCollection());
        nbMinions++;
    }

    @FXML
    public void startFight() {

        CPersonage companion = companions.get(fightPos);
        CPersonage minion = minions.get(fightPos);

        FightResult fightResult = fight(companion, minion);
        switch (fightResult.getAction()) {
            case WOUND:
                fightResult.getPersonage().addWound();
                break;
            case KILL:
                CPersonage personage = (CPersonage) fightResult.getPersonage();
                try {
                    if (FREE_PEOPLE == personage.getPersonage().getSide()) {
                        companions.get(companions.indexOf(personage)).kill();
                        nbCompanions--;
                    } else {
                        minions.get(minions.indexOf(personage)).kill();
                        nbMinions--;
                    }
                } catch (EndGameException ege) {
                    action.setDisable(true);
                }
        }

        if(++fightPos >= Math.min(nbCompanions, nbMinions)) {
            action.setText("Next turn");
            action.setOnAction( event -> {
                Toastr.append("Start next turn");
                fightPos = 0;
                action.setText("Fight");
                action.setOnAction(event1 -> startFight());
            });
        }
    }
}
