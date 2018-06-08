package com.blockout22.rpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.training.mobs.player.Player;
import com.blockout22.rpg.Statics;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class GameScreen extends ScreenStage {

    private VisTextButton train;
    private VisTextButton boss;
    private VisTextButton playerStats;

    public GameScreen(Game g, Player player){
        super(g, player);
        boss = new VisTextButton("Fight Boss");
        train = new VisTextButton("Train");
        playerStats = new VisTextButton("Player Stats");

        train.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getGame().setScreen(Statics.TRAINING_SCREEN);
            }
        });

        playerStats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getGame().setScreen(Statics.PLAYER_STATS_SCREEN);
            }
        });

        boss.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getGame().setScreen(Statics.BOSS_BATTLE_SCREEN);
            }
        });

        rootTable.add(train).fillX().pad(5);
        rootTable.row();
        rootTable.add(boss).fillX().pad(5);
        rootTable.row();
        rootTable.add(playerStats).fillX().pad(5);
        rootTable.row();
        rootTable.add(Statics.createBackButton(Statics.MAIN_MENU)).expand().bottom().right();
    }
}
