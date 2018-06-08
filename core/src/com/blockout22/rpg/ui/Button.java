package com.blockout22.rpg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Button extends TextButton {
    public Button(String text) {
        super(text, GUI.getSkin());
//        GUI
        this.setHeight(Gdx.graphics.getHeight() / 2);
    }

    public Button(){
        this("");
    }
}
