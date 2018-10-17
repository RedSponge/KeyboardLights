package com.redsponge.lights;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardManager {

    public static KeyboardManager INSTANCE;

    private Robot r;

    private boolean[] keys;

    public KeyboardManager() {
        try {
            r = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        keys = new boolean[] {false, false, false};
//        allOff();
    }

    public void toggle(Light l) {
//        System.out.println("AAA");
        r.keyPress(l.key);
        r.keyRelease(l.key);
        keys[l.id] = !keys[l.id];
    }

    public void setState(boolean a, boolean b, boolean c) {
         if(a && !keys[0] || !a && keys[0]) {
             toggle(Light.LEFT);
         }

        if(b && !keys[1] || !b && keys[1]) {
            toggle(Light.MIDDLE);
        }

        if(c && !keys[2] || !c && keys[2]) {
            toggle(Light.RIGHT);
        }
    }

    public void end()
    {
        setState(false, false, false);
    }

    public void allOff() {
//        System.out.println("toggle");
        if(Toolkit.getDefaultToolkit().getLockingKeyState(Light.LEFT.key)) {
//            System.out.println("TURN OFF LEFT");
            toggle(Light.LEFT);
            }if(Toolkit.getDefaultToolkit().getLockingKeyState(Light.MIDDLE.key)) {
//            System.out.println("TURN OFF MID");
            toggle(Light.MIDDLE);
        }if(Toolkit.getDefaultToolkit().getLockingKeyState(Light.RIGHT.key)) {
//            System.out.println("TURN OFF RIGHT");
            toggle(Light.RIGHT);
        }
        try {
            Thread.sleep(400);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++) {
            keys[i] = false;
        }

    }


    public enum Light {
        LEFT(KeyEvent.VK_NUM_LOCK,0),
        MIDDLE(KeyEvent.VK_CAPS_LOCK,1),
        RIGHT(KeyEvent.VK_SCROLL_LOCK,2);

        private final int key;
        private final int id;

        Light(int key, int id) {
            this.key = key;
            this.id = id;
        }
    }

}
