package com.redsponge.lights;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(Integer.parseInt("1001", 2));
        ScriptPlayer player = new ScriptPlayer(new File("C:\\cool.lightscript"));

        player.run();
    }

}
