package com.blockout22.rpg.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.training.mobs.player.Player;
import com.blockout22.rpg.Statics;
import com.blockout22.rpg.ui.Button;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MainMenuScreen extends ScreenStage {

    private VisTextButton play;
    private VisTextButton options;
    private VisTextButton fullscreen;

    public MainMenuScreen(Game g, Player player){
        super(g, player);
        play = new VisTextButton("Click To Play");
        options = new VisTextButton("Options");
        fullscreen = new VisTextButton("Toggle Fullscreen");
//        rootTable.align(Align.top);

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getGame().setScreen(Statics.GAME_SCREEN);
            }
        });

        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getGame().setScreen(Statics.OPTIONS_SCREEN);
            }
        });

        fullscreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Graphics.Monitor currMonitor = Gdx.graphics.getMonitor();
                Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode(currMonitor);
                if(!Gdx.graphics.isFullscreen()) {
                    // switching to full-screen mode failed
                    Gdx.graphics.setFullscreenMode(displayMode);
                }else{
                    Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                }
            }
        });

        rootTable.add(play).fillX().pad(5);
        rootTable.row();
        rootTable.add(options).fillX().pad(5);
        rootTable.row();
        if(Gdx.app.getType() == Application.ApplicationType.Desktop && false) {
            rootTable.add(fullscreen).fillX().pad(5);
            rootTable.row();
        }
        rootTable.add().expand();
    }
}
