package com.blockout22.rpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.Statics;
import com.blockout22.rpg.training.mobs.Mob;
import com.blockout22.rpg.training.mobs.player.Player;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class BossBattleScreen extends ScreenStage{

    private Table scrollTable;
    private VisScrollPane scrollPane;

    public BossBattleScreen(final Game g, final Player player) {
        super(g, player);
        scrollTable = new Table();
        scrollPane = new VisScrollPane(scrollTable);
        scrollPane.layout();
        scrollPane.setFadeScrollBars(false);

        for(int i = 0; i < Statics.bossMobs.length; i++){
            final Mob mob = Statics.bossMobs[i].getMob();

            boolean free = Statics.isFree;
            VisTextButton t = new VisTextButton(Statics.bossMobs[i].getMob().getName() + (free && !Statics.bossMobs[i].isFree() ? " (Paid Version)" : ""));

            if(free && !Statics.bossMobs[i].isFree()){
                t.setDisabled(true);
            }

            VisTextButton tip = new VisTextButton("i");

            t.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    getGame().setScreen(new FightScreen(g, player, mob, BossBattleScreen.this));
                }
            });

            tip.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    getGame().setScreen(new InfoScreen(g, player, BossBattleScreen.this, "Information about: " + mob.getName(), mob.getInfo()));
                }
            });

            scrollTable.add(t).fillX().pad(5);
            scrollTable.add(tip).pad(5);
            scrollTable.row();
        }

        scrollTable.row();
        scrollTable.add().expand();
        rootTable.add(scrollPane).fill().expand();
        rootTable.row();
        rootTable.add(Statics.createBackButton(Statics.GAME_SCREEN)).expand().bottom().right();
    }
}
