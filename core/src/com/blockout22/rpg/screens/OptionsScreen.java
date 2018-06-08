package com.blockout22.rpg.screens;

import com.badlogic.gdx.Game;
import com.blockout22.rpg.training.mobs.player.Player;
import com.blockout22.rpg.Statics;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;

public class OptionsScreen extends ScreenStage {

    private VisLabel titleLabel, volumeMusicLabel, musicOnOffLabel, volumeSoundLabel, soundOnOffLabel;
    private VisSlider volumeMusicSlider, soundMusicSlider;
    private VisCheckBox musicCheckbox, soundEffectsCheckbox;

    public OptionsScreen(Game g, Player player) {
        super(g, player);
        titleLabel = new VisLabel("Preferences");
        volumeMusicLabel = new VisLabel("Music Volume");
        musicOnOffLabel = new VisLabel("Music");
        volumeSoundLabel = new VisLabel("Sound Volume");
        soundOnOffLabel = new VisLabel("Sound Effect");

        volumeMusicSlider = new VisSlider(0f, 1f, 0.1f, false);
        soundMusicSlider = new VisSlider(0f, 1f, 0.1f, false);

        musicCheckbox = new VisCheckBox(null);
        soundEffectsCheckbox = new VisCheckBox(null);

        rootTable.add(titleLabel);
        rootTable.row();
        rootTable.add(volumeMusicLabel);
        rootTable.add(volumeMusicSlider);
        rootTable.row();
        rootTable.add(musicOnOffLabel);
        rootTable.add(musicCheckbox);
        rootTable.row();
        rootTable.add(volumeSoundLabel);
        rootTable.add(soundMusicSlider);
        rootTable.row();
        rootTable.add(soundOnOffLabel);
        rootTable.add(soundEffectsCheckbox);
        rootTable.row();
        rootTable.add(Statics.createBackButton(Statics.MAIN_MENU));
    }
}
