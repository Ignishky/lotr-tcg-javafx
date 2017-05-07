package fr.ducloyer.lotrtcg.controller;

import org.junit.BeforeClass;
import org.testfx.framework.junit.ApplicationTest;

public abstract class AbstractControllerTest extends ApplicationTest {

    @BeforeClass
    public static void configTest() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }
}
