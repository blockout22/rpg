package com.blockout22.rpg.training.mobs.player;

import com.blockout22.rpg.Statics;
import com.blockout22.rpg.training.mobs.Mob;
import com.blockout22.rpg.training.mobs.Stats;

public class Player extends Mob{

//    private long xp;
    //the amount of xp the player has saved and can use on any stat they choose
    private long xpBank = 0;
    private XpData strengthLevel, healthLevel;

    public Player() {
        super("Player", new Stats(0, 0));
        boolean requiresFlush = false;

        strengthLevel = new XpData();
        healthLevel = new XpData();

        //load all player stats from file
        if (!Statics.prefs.contains(Statics.PLAYER_MAX_HEALTH_XP)) {
            Statics.prefs.putLong(Statics.PLAYER_MAX_HEALTH_XP, healthLevel.levelToXp(10));
            requiresFlush = true;
        }

        if(!Statics.prefs.contains(Statics.PLAYER_STRENGTH_XP)){
            Statics.prefs.putLong(Statics.PLAYER_STRENGTH_XP, 1);
            requiresFlush = true;
        }

        if(!Statics.prefs.contains(Statics.PLAYER_SPEED)){
            Statics.prefs.putLong(Statics.PLAYER_SPEED, 5000);
            requiresFlush = true;
        }

        if(!Statics.prefs.contains(Statics.PLAYER_XP_BANK)){
            Statics.prefs.putLong(Statics.PLAYER_XP_BANK, 0);
            requiresFlush = true;
        }

        if(requiresFlush){
            Statics.prefs.flush();
        }

        long maxhealth_xp = Statics.prefs.getLong(Statics.PLAYER_MAX_HEALTH_XP);
        long strength_xp = Statics.prefs.getLong(Statics.PLAYER_STRENGTH_XP);
        //change speed based on weapon equipped
        long speed = Statics.prefs.getLong(Statics.PLAYER_SPEED);

        healthLevel.setXp(maxhealth_xp);
        strengthLevel.setXp(strength_xp);
        xpBank = Statics.prefs.getLong(Statics.PLAYER_XP_BANK);


        this.getStats().setStrength(strengthLevel.getLevel());
        this.getStats().setMaxhealth(healthLevel.getLevel());
        this.getStats().setCurrentHealth(getStats().getMaxhealth());
        setAttackSpeed(speed);
    }

    public XpData getStrengthXpData(){
        return strengthLevel;
    }

    public XpData getHealthXpData(){
        return healthLevel;
    }

    public void rewardXp(long xp){
       xpBank += xp;
       Statics.prefs.putLong(Statics.PLAYER_XP_BANK, xpBank);
       Statics.prefs.flush();
    }

    public long getXpBank(){
        return xpBank;
    }

    //calculates xp based on damage dealt;
//    public void calcXp(long dmg){
//        //if damage is 0 then no damage is rewarded
//        if(dmg <= 0){
//            return;
//        }
//
//        if(dmg == 1){
//            strengthLevel.addXp(1);
//        }else{
//            long xp = dmg / 2;
//            strengthLevel.addXp(xp);
//            healthLevel.addXp(xp / 2);
//        }
//
//        updatePrefs();
//    }

    private void updatePrefs() {
            Statics.prefs.putLong(Statics.PLAYER_MAX_HEALTH_XP, healthLevel.getXp());
            Statics.prefs.putLong(Statics.PLAYER_STRENGTH_XP, strengthLevel.getXp());
    }


//    /**
//     * hits the mob with a random number based on stats
//     */
//    public void hit(Mob mob){
//
//    }
//
//    /**
//     * the amount of damage to deal to the player
//     */
//    public void damage(long amt){
//        this.getStats().setCurrentHealth(this.getStats().getCurrentHealth() - amt);
//    }
}
