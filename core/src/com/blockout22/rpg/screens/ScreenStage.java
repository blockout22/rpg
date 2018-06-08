package com.blockout22.rpg.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.*;
import com.blockout22.rpg.listeners.OnShownListener;
import com.blockout22.rpg.training.mobs.player.Player;

public abstract class ScreenStage implements Screen {


    //listeners;
    private OnShownListener onShown;

    private Game game;
    private Player player;
    public Stage stage;
    public Table rootTable;

    public ScreenStage(final Game g, final Player player)
    {
        this.game = g;
        this.player = player;
//        OrthographicCamera cam = new OrthographicCamera(256, 256);
//        ScreenViewport viewport = new ScreenViewport(cam);
//        ExtendViewport viewport = new ExtendViewport(256, 256);
//        ScalingViewport viewport = new ScalingViewport(Scaling.stretch, 500, 500);
//        FillViewport viewport = new FillViewport(500, 500);
//        FitViewport viewport = new FitViewport(500, 500);
        StretchViewport viewport = new StretchViewport(500, 500);
        stage = new Stage(viewport);
        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);
    }

    public void setOnShownListener(OnShownListener listener){
        this.onShown = listener;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        if(onShown != null){
            onShown.call();
        }
    }

    @Override
    public void render(float delta){
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose()
    {
        stage.dispose();
    }
}
