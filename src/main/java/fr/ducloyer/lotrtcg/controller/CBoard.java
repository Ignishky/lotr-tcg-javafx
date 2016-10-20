package fr.ducloyer.lotrtcg.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class CBoard implements Initializable {

    @FXML
    private CPersonage companion1Controller;

    @FXML
    private CPersonage companion2Controller;

    @FXML
    private CPersonage minion1Controller;

    @FXML
    private CPersonage minion2Controller;

    @FXML
    private Button fight;

    @FXML
    private TextArea info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Toastr.init(info);
        companion1Controller.addPersonage(1290);
        companion2Controller.addPersonage(1364);
        minion1Controller.addPersonage(1176);
        minion2Controller.addPersonage(1191);
    }

    @FXML
    public void startFight() {
        companion1Controller.addWound();
        Toastr.append(companion1Controller.getName() + " has been wounded.");
    }
}
