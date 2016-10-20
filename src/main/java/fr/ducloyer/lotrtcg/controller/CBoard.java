package fr.ducloyer.lotrtcg.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.google.common.collect.Lists.newArrayList;

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

    @FXML
    private TextArea info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Toastr.init(info);
        companions = newArrayList(companion1Controller, companion2Controller);
        minions = newArrayList(minion1Controller, minion2Controller);
    }

    public void addCompanion(int collection) {
        companions.get(nbCompanions).addPersonage(collection);
        nbCompanions++;
    }

    public void addMinion(int collection) {
        minions.get(nbMinions).addPersonage(collection);
        nbMinions++;
    }

    @FXML
    public void startFight() {
        companion1Controller.addWound();
        Toastr.append(companion1Controller.getName() + " has been wounded.");
    }
}
