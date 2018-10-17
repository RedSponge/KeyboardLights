package com.redsponge.lights;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

public class ScriptManager {

    private HashMap<String, ScriptPlayer> scripts;

    public ScriptManager() {
        scripts = new HashMap<String, ScriptPlayer>();
    }

    public void add(InputStream script, String name) {
        scripts.put(name, new ScriptPlayer(script));
    }

    public void loop(String name, int delay) {
        KeyboardManager.INSTANCE.allOff();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scripts.get(name).loop(delay);
    }
}
