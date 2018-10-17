package com.redsponge.lights;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ScriptPlayer {

    private int[] states;

    private boolean loop;

    private InputStream in;

    public ScriptPlayer(InputStream in) {

        this.in = in;
        this.loop = false;

        readFile();
    }

    public void loop(int delay) {
        while(true) {
            run(delay);
        }
    }

    public void run(int delay) {
        for(int state : states) {

            boolean first = (state & 0b100) == 0b100;
            boolean second = (state & 0b010) == 0b010;
            boolean third = (state & 0b001) == 0b001;

            KeyboardManager.INSTANCE.setState(first, second, third);
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder text = new StringBuilder();
            String s;
            while((s = reader.readLine()) != null) {
                text.append(s).append("\n");
            }


            String[] lines = text.toString().split("\n");
            states = new int[lines.length];
            for (int i = 0; i < lines.length; i++) {
                states[i] = Integer.parseInt(lines[i], 2);
            }

            System.out.println("Loaded script!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
