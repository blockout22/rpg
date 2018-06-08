package com.blockout22.rpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.blockout22.rpg.training.mobs.player.Player;
import com.blockout22.rpg.Statics;
import com.blockout22.rpg.training.mobs.Mob;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class FightScreen extends ScreenStage{

    private Mob mob;

    private Table bottomBar;

    private VisLabel mobName;
    private VisProgressBar mobHealth, playerHealth;
    private VisTextButton attackButton, backConfirm;
    private VisDialog dialog;

    private String ATTACK_STRING = "Attack";

    private long lastMobHit, lastPlayerHit;

    public FightScreen(Game g, final Player player, final Mob mob, ScreenStage lastScreen)
    {
        super(g, player);
        this.mob = mob;
        bottomBar = new Table();

        mobName = new VisLabel(mob.getName());

        mobHealth = new VisProgressBar(0, mob.getStats().getMaxhealth(), 0.1f, false);
        mobHealth.setValue(mob.getStats().getCurrentHealth());

        playerHealth = new VisProgressBar(0, player.getStats().getMaxhealth(), 0.1f, false);
        playerHealth.setValue(player.getStats().getCurrentHealth());

        attackButton = new VisTextButton("Attack");
        backConfirm = new VisTextButton("Back");

        dialog = new VisDialog("Are you sure you want to go back?");

        attackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                attackButton.setDisabled(true);
                long dmg = getPlayer().hit(mob);
//                player.calcXp(dmg);
                lastPlayerHit = System.currentTimeMillis();
            }
        });

        backConfirm.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dialog.show(stage);
            }
        });

        rootTable.add(mobName).top();
        rootTable.row();
        rootTable.add(mobHealth).top().fillX().expand();
        rootTable.row();
        rootTable.add(playerHealth).fillX();
        rootTable.row();
        rootTable.add(bottomBar).fillX();
        bottomBar.add(attackButton).center().pad(5);
        bottomBar.add(backConfirm).expand().right();


        VisTextButton back = Statics.createBackButton(lastScreen);
        back.setText("Yes");
        dialog.button(back);
        dialog.button("No");

        lastMobHit = System.currentTimeMillis();
        lastPlayerHit = System.currentTimeMillis();

        player.reset();
        mob.reset();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(!mob.isDead() && !getPlayer().isDead()) {
            if (TimeUtils.timeSinceMillis(lastMobHit) > mob.getAttackSpeed()) {
                mob.hit(getPlayer());
                //TODO get mob stats and calculate damage to deal to player
                lastMobHit = System.currentTimeMillis();
            }

            if(attackButton.isDisabled()){
                long t = getPlayer().getAttackSpeed() - (TimeUtils.timeSinceMillis(lastPlayerHit));
                attackButton.setText(((t / 1000) + 1) + " seconds");
            }
            if(TimeUtils.timeSinceMillis(lastPlayerHit) > getPlayer().getAttackSpeed()){
                attackButton.setDisabled(false);
                attackButton.setText(ATTACK_STRING);
            }

            Gdx.graphics.setTitle("" + (getPlayer().getAttackSpeed() - (TimeUtils.timeSinceMillis(lastPlayerHit))));

        }else{
//            mob.reset();
//            getPlayer().reset();
            if(getPlayer().isDead()){
                getGame().setScreen(Statics.DEATH_SCREEN);
            }

            if(mob.isDead()){
                getPlayer().rewardXp(mob.getRewardXp());
                WinTrainingScreen wts = (WinTrainingScreen) Statics.WIN_TRAINING_SCREEN;
                getGame().setScreen(wts.append("\nyou gained " + mob.getRewardXp() + " xp" ));
            }
        }

        mobHealth.setValue(mob.getStats().getCurrentHealth());
        playerHealth.setValue(getPlayer().getStats().getCurrentHealth());
    }
}
