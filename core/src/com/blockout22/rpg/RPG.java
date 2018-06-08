package com.blockout22.rpg;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Version;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.blockout22.rpg.ui.GUI;
import com.kotcrab.vis.ui.VisUI;

import java.io.File;

public class RPG extends Game {

//    public static Skin skin;


	@Override
	public void create () {
	    File f = new File("");
        System.out.println(f.getAbsolutePath());
//	    Statics.printOSInfo();
//        System.out.println("Create");
        Gdx.input.setCatchBackKey(true);


        System.out.println("Version: " + Version.VERSION);

        GUI.load();

//        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

	    VisUI.load(VisUI.SkinScale.X2);
//        VisUI.load(skin);
	    Statics.init(this);
	    this.setScreen(Statics.MAIN_MENU);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

    @Override
    public void resize(int width, int height) {
	    super.resize(width, height);
	}

    @Override
    public void resume() {
//        System.out.println("Resume");
        super.resume();
    }

    @Override
    public void pause() {
//        System.out.println("Pause");
        Statics.prefs.flush();
        super.pause();
    }

    @Override
	public void dispose () {
//        System.out.println("Dispose");
        GUI.dispose();
        Statics.dispose();
        VisUI.dispose();
//        skin.dispose();
	}
}
