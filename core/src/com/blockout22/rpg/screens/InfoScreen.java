package com.blockout22.rpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Align;
import com.blockout22.rpg.training.mobs.player.Player;
import com.blockout22.rpg.Statics;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;

public class InfoScreen extends ScreenStage{

    private VisScrollPane scroll;
    private VisLabel visTitle, infoText;

    /**
     * the last screen which is used to create the back button to the screen which called this
     * @param lastScreen
     */
    public InfoScreen(Game g, Player player, ScreenStage lastScreen, String title, String text){
        super(g, player);
        visTitle = new VisLabel(title);
        infoText = new VisLabel();
        scroll = new VisScrollPane(infoText);
        visTitle.setWrap(true);
        infoText.setWrap(true);
        infoText.setText(text);
        infoText.pack();
        scroll.layout();

        rootTable.add(visTitle).fillX().align(Align.center);
        rootTable.row();
        rootTable.add(scroll).fill().expand();
        rootTable.row();
        rootTable.add(Statics.createBackButton(lastScreen));
    }
}
