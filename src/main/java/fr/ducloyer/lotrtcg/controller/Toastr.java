package fr.ducloyer.lotrtcg.controller;

import javafx.scene.control.TextArea;

import static java.lang.System.lineSeparator;

public class Toastr {

    private static TextArea textArea;

    public static void init(TextArea info) {
        textArea = info;
    }

    public static void append(String message) {
        textArea.appendText(message + lineSeparator());
    }
}
