package com.redsponge.lights;

import com.redsponge.lights.KeyboardManager.Light;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel closer = new JLabel("LOOK AT YOUR NUMLOCK, CAPS LOCK AND THE OTHER THING!   also close window to exit");
        closer.setSize(50, 50);
        frame.getContentPane().add(closer);
        frame.pack();
        frame.setVisible(true);

        KeyboardManager.INSTANCE = new KeyboardManager();
        ScriptManager scriptManager = new ScriptManager();
        scriptManager.add(Main.class.getResourceAsStream("/cool.lightscript"), "cool");
        scriptManager.loop("cool", 200);
    }

}
