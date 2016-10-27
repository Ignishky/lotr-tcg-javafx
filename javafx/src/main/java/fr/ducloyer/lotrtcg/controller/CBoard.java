package fr.ducloyer.lotrtcg.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.google.common.collect.Lists.newArrayList;

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
    private Button fight;
    private int fightPos = 0;

    @FXML
    private TextArea info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Toastr.init(info);
        companions = newArrayList(companion1Controller, companion2Controller);
        minions = newArrayList(minion1Controller, minion2Controller);
    }

    public void addCompanion(int collection) {
        log.info("Add companion {}", collection);
        companions.get(nbCompanions).addPersonage(collection);
        nbCompanions++;
    }

    public void addMinion(int collection) {
        log.info("Add minion {}", collection);
        minions.get(nbMinions).addPersonage(collection);
        nbMinions++;
    }

    @FXML
    public void startFight() {
        CPersonage companion = companions.get(fightPos);
        CPersonage minion = minions.get(fightPos);

        if(companion.getStrength() > minion.getStrength()) {
            minion.addWound();
        } else {
            companion.addWound();
        }
        fightPos++;
    }
}
