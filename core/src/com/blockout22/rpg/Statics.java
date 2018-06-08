package com.blockout22.rpg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.blockout22.rpg.screens.*;
import com.blockout22.rpg.training.mobs.*;
import com.blockout22.rpg.training.mobs.bosses.MobHollowFalcon;
import com.blockout22.rpg.training.mobs.player.Player;
import com.kotcrab.vis.ui.widget.VisTextButton;

import java.util.Locale;

public class Statics {

//    private Game game;
    public static Preferences prefs;

//    public static Array<MobArray> trainingMobs = new Array<MobArray>();
    public static MobArray[] trainingMobs = new MobArray[3];
    public static MobArray[] bossMobs = new MobArray[1];

    public static boolean isFree = true;

    public static float BUTTON_HEIGHT = Gdx.graphics.getHeight() / 4;

    public static ScreenStage
            MAIN_MENU,
            GAME_SCREEN,
            OPTIONS_SCREEN,
            TRAINING_SCREEN,
            DEATH_SCREEN,
            WIN_TRAINING_SCREEN,
            PLAYER_STATS_SCREEN,
            BOSS_BATTLE_SCREEN;

    public static final String
            PLAYER_MAX_HEALTH_XP = "maxhealth",
            PLAYER_STRENGTH_XP = "strength",
            PLAYER_SPEED = "attack-speed",
            PLAYER_XP_BANK = "xp-bank";


    public static void init(Game g) {
//        game = g;
        prefs = Gdx.app.getPreferences("userdata");

        FileHandle fileHandler = Gdx.files.internal("i18n/bundle");
        Locale locale = new Locale("en");
        I18NBundle bundle = I18NBundle.createBundle(fileHandler, locale);

        //needs creating before screen, otherwise TrainingScreen will check array size when creating UI and size will be 0

        trainingMobs[0] = new MobArray(true, new MobRat());
        trainingMobs[1] = new MobArray(true, new MobZombie());
        trainingMobs[2] = new MobArray(false, new MobUnicorn());

        bossMobs[0] = new MobArray(true, new MobHollowFalcon());
//        addTrainingMob(0, new MobRat());
//
//        addTrainingMob(1, new MobZombie());
//        if(!isFree)
//            addTrainingMob(2, new TestMob());



        Player player = new Player();

        MAIN_MENU = new MainMenuScreen(g, player);
        GAME_SCREEN = new GameScreen(g, player);
        OPTIONS_SCREEN = new OptionsScreen(g, player);
        TRAINING_SCREEN = new TrainingScreen(g, player);
        DEATH_SCREEN = new DeathScreen(g, player);
        WIN_TRAINING_SCREEN = new WinTrainingScreen(g, player);
        PLAYER_STATS_SCREEN = new PlayerStatsScreen(g, player);
        BOSS_BATTLE_SCREEN = new BossBattleScreen(g, player);

        //Calling new Info screen when needed till I can think of a solution
//        INFO_SCREEN = new InfoScreen(null);

        String[] nameList = NameGenerator.getNames(15);

        for(String s : nameList){
            System.out.println("NameGen: " + s);
        }
    }



//    private static void addTrainingMob(int index, Mob mob){
//        for(int i = 0; i < trainingMobs.size; i++){
//            if(trainingMobs.get(i).getIndex() == index)
//            {
//                   throw new IllegalArgumentException(index + " Already Exists");
//            }
//        }
//
//        trainingMobs.add(new MobArray(index, mob));
//    }

//    public static void changeScreen(Screen screen) {
//        this.game.setScreen(screen);
//    }

    /**
     * create a back button and takes in the screen which needs to be shown when the button is clicked
     */
    public static VisTextButton createBackButton(final ScreenStage screen){
        VisTextButton backButton = new VisTextButton("Back");
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                prefs.flush();
                screen.getGame().setScreen(screen);
            }
        });

        return backButton;
    }

    static void dispose() {
        prefs.flush();
        MAIN_MENU.dispose();
        GAME_SCREEN.dispose();
        OPTIONS_SCREEN.dispose();
        TRAINING_SCREEN.dispose();
        DEATH_SCREEN.dispose();
        WIN_TRAINING_SCREEN.dispose();
        PLAYER_STATS_SCREEN.dispose();
        BOSS_BATTLE_SCREEN.dispose();
    }

    //print some info about the system
    static void printOSInfo(){
        System.out.println("===== OS Information =====");
        System.err.println("This Information is only for debug purposes \n(A Note for the dev: why am I printing this?)");

        System.out.println("User:" + System.getProperty("user.name"));
        System.out.println("User Home: " + System.getProperty("user.home"));
        System.out.println("User Directory: " + System.getProperty("user.dir"));
        System.out.println("User Country: " + System.getProperty("user.country"));
        System.out.println("User Language: " + System.getProperty("user.language"));

        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));

        System.out.println("Java: " + System.getProperty("java.runtime.name"));
        System.out.println("Java Vendor: " + System.getProperty("java.vendor"));
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Directory: " + System.getProperty("java.home"));

        Runtime rt = Runtime.getRuntime();
        System.out.println("Processor Cores: " + rt.availableProcessors());
        System.out.println("Free Memory: " + rt.freeMemory());
        System.out.println("Maximum Memory: " + rt.maxMemory());
        System.out.println("Total Memory: " + rt.totalMemory());

        System.out.println("==========================");
    }

}
