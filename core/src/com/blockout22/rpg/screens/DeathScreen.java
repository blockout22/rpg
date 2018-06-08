package com.blockout22.rpg.screens;

import com.badlogic.gdx.Game;
import com.blockout22.rpg.training.mobs.player.Player;
import com.blockout22.rpg.Statics;
import com.kotcrab.vis.ui.widget.VisLabel;

public class DeathScreen extends ScreenStage{

    private VisLabel text;

    public DeathScreen(Game g, Player player) {
        super(g, player);

        text = new VisLabel("You Died!");

        rootTable.add(text).expand().bottom();
        rootTable.row();
        rootTable.add(Statics.createBackButton(Statics.TRAINING_SCREEN)).expand().bottom().right();
    }
}
