package com.blockout22.rpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.blockout22.rpg.Statics;
import com.blockout22.rpg.listeners.OnShownListener;
import com.blockout22.rpg.training.mobs.player.Player;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class PlayerStatsScreen extends ScreenStage{

    private VisLabel pStats;
    private VisLabel pInfo;
    private VisScrollPane statsScroll, xpScroll;
    private String info;

    private VisTable xpTable;
    private VisTextButton addStrengthXp, addHealthXp;
    private VisLabel xpBank, strengthXp, healthXp;

    public PlayerStatsScreen(Game g, final Player player) {
        super(g, player);

        info = "Attacking Speed: " + player.getAttackSpeed() + " ms"
                + "\nStrength: " + player.getStats().getStrength()
                + "\nHealth: " + player.getStats().getMaxhealth();

        pStats = new VisLabel("Player Stats");
        pStats.setAlignment(Align.center);

        xpTable = new VisTable();
        addHealthXp = new VisTextButton("+");
        addStrengthXp = new VisTextButton("+");

        xpBank = new VisLabel("Xp Bank: " + player.getXpBank());
        healthXp = new VisLabel("Health Xp: " + player.getHealthXpData().getXp());
        strengthXp = new VisLabel("Strength Xp: " + player.getStrengthXpData().getXp());

        pInfo = new VisLabel(info);
        statsScroll = new VisScrollPane(pInfo);
        xpScroll = new VisScrollPane(xpTable);

        addHealthXp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long healthExp = player.getHealthXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank <= 0){
//                    addHealthXp.setDisabled(true);
                    return;
                }
                healthExp += 1;
                player.getHealthXpData().setXp(healthExp);
                Statics.prefs.putLong(Statics.PLAYER_MAX_HEALTH_XP, healthExp);
                player.rewardXp(-1);

                xpBank.setText("Xp Bank: " + player.getXpBank());
                healthXp.setText("Health Xp: " + player.getHealthXpData().getXp());

                player.getStats().setMaxhealth(player.getHealthXpData().xpToLevel(healthExp));

                info = "Attacking Speed: " + player.getAttackSpeed() + " ms"
                        + "\nStrength: " + player.getStats().getStrength()
                        + "\nHealth: " + player.getStats().getMaxhealth();

                pInfo.setText(info);
            }
        });

        addStrengthXp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long strengthExp = player.getStrengthXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank <= 0){
//                    addHealthXp.setDisabled(true);
                    return;
                }
                strengthExp += 1;
                player.getStrengthXpData().setXp(strengthExp);
                Statics.prefs.putLong(Statics.PLAYER_STRENGTH_XP, strengthExp);
                player.rewardXp(-1);

                xpBank.setText("Xp Bank: " + player.getXpBank());
                strengthXp.setText("Strength Xp: " + player.getStrengthXpData().getXp());

                player.getStats().setStrength(player.getStrengthXpData().xpToLevel(strengthExp));

                info = "Attacking Speed: " + player.getAttackSpeed() + " ms"
                        + "\nStrength: " + player.getStats().getStrength()
                        + "\nHealth: " + player.getStats().getMaxhealth();

                pInfo.setText(info);
            }
        });

        xpTable.addSeparator();
        xpTable.add(xpBank).pad(5);
        xpTable.row();
        xpTable.add(healthXp).pad(5);
        xpTable.add(addHealthXp).pad(5);
        xpTable.row();
        xpTable.add(strengthXp).pad(5);
        xpTable.add(addStrengthXp).pad(5);

        rootTable.add(pStats).fillX();
        rootTable.row();
        rootTable.add(statsScroll);
        rootTable.row();
        rootTable.add(xpScroll);

        rootTable.row();
        rootTable.add(Statics.createBackButton(Statics.GAME_SCREEN)).expand().bottom().right();

        setOnShownListener(new OnShownListener() {
            @Override
            public void call() {
                System.out.println("Calling");
                info = "Attacking Speed: " + player.getAttackSpeed() + " ms"
                        + "\nStrength: " + player.getStats().getStrength()
                        + "\nHealth: " + player.getStats().getMaxhealth();

                pStats.setText("Player Stats");
                pInfo.setText(info);

                xpBank.setText("Xp Bank: " + player.getXpBank());
                healthXp.setText("Health Xp: " + player.getHealthXpData().getXp());
                strengthXp.setText("Strength Xp: " + player.getStrengthXpData().getXp());
            }
        });
    }
}
