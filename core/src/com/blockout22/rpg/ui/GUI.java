package com.blockout22.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GUI {

    private static Skin skin;

    public static void load(Skin skin){
        GUI.skin = skin;
    }

    public static void load(){
        GUI.skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
    }

    public static Skin getSkin(){
        return skin;
    }

    public static void dispose(){
        skin.dispose();
    }
}
