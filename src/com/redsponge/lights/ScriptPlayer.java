package com.redsponge.lights;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class ScriptPlayer {

    private KeyboardManager manager;
    private int[] states;

    private boolean loop;
    private int delay;

    private File f;

    public ScriptPlayer(File f) {
        manager = new KeyboardManager();

        this.f = f;
        this.loop = false;
        this.delay = 0;

        readFile();
        if(loop) doLoop();
        else run();
    }

    private void doLoop() {
        while(true) {
            run();
        }
    }

    public void run() {
        for(int state : states) {
            String s = Integer.toBinaryString(state);

            while(s.length() < 3) {
                s = "0" + s;
            }
//            System.out.println(s);
            boolean first = (s.charAt(0) == '1');
            boolean second = (s.charAt(1) == '1');
            boolean third = (s.charAt(2) == '1');

            manager.setState(first, second, third);
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
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String state = reader.readLine();
            if(state.split(" ")[0].equals("loop")) {
                loop = true;
            }
            delay = Integer.parseInt(state.split(" ")[1]);

            String rest = "";
            String s;
            while((s = reader.readLine()) != null) {
                rest += s + "\n";
            }

            String[] lines = rest.split("\n");
            states = new int[lines.length];
            for (int i = 0; i < lines.length; i++) {
                states[i] = Integer.parseInt(lines[i], 2);
            }

            System.out.println("Loaded!");
            System.out.println("Loop: " + loop);
            System.out.println("Delay: " + delay);
            System.out.println("States: \n" + Arrays.toString(states));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
