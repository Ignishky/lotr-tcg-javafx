package fr.ducloyer.lotrtcg.controller;

import fr.ducloyer.lotrtcg.core.model.Card.Name;
import fr.ducloyer.lotrtcg.core.utils.EndGameException;
import fr.ducloyer.lotrtcg.core.utils.FightResult;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private Parent companion1;
    @FXML
    private CPersonage companion1Controller;
    @FXML
    private Parent companion2;
    @FXML
    private CPersonage companion2Controller;

    private int nbCompanions = 0;
    private List<Parent> companionsViews;
    private List<CPersonage> companionsControllers;

    @FXML
    private Parent minion1;
    @FXML
    private CPersonage minion1Controller;
    @FXML
    private Parent minion2;
    @FXML
    private CPersonage minion2Controller;

    private int nbMinions = 0;
    private List<Parent> minions;
    private List<CPersonage> minionsControllers;

    @FXML
    private Button action;
    private int fightPos = 0;

    @FXML
    private TextArea info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Toastr.init(info);
        companionsViews = newArrayList(companion1, companion2);
        companionsControllers = newArrayList(companion1Controller, companion2Controller);
        minions = newArrayList(minion1, minion2);
        minionsControllers = newArrayList(minion1Controller, minion2Controller);
    }

    public void addCompanion(Name card) {
        addCompanion(card, 0);
    }

    public void addCompanion(Name card, int nbWounds) {
        log.info("Add companion {}", card.getCollection());
        companionsControllers.get(nbCompanions).addPersonage(card.getCollection(), nbWounds);
        companionsViews.get(nbCompanions).setVisible(true);
        nbCompanions++;
    }

    public void addMinion(Name card) {
        addMinion(card, 0);
    }

    public void addMinion(Name card, int nbWounds) {
        log.info("Add minion {}", card.getCollection());
        minionsControllers.get(nbMinions).addPersonage(card.getCollection(), nbWounds);
        minions.get(nbMinions).setVisible(true);
        nbMinions++;
    }

    @FXML
    public void startFight() {

        CPersonage companion = companionsControllers.get(fightPos);
        CPersonage minion = minionsControllers.get(fightPos);

        FightResult fightResult = fight(companion, minion);
        switch (fightResult.getAction()) {
            case WOUND:
                fightResult.getPersonage().addWound();
                break;
            case KILL:
                CPersonage personage = (CPersonage) fightResult.getPersonage();
                try {
                    if (FREE_PEOPLE == personage.getPersonage().getSide()) {
                        nbCompanions--;
                        companionsControllers.get(companionsControllers.indexOf(personage)).kill();
                    } else {
                        nbMinions--;
                        minionsControllers.get(minionsControllers.indexOf(personage)).kill();
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
